package com.myRetail.products.service;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.myRetail.products.exception.DataNotFoundException;
import com.myRetail.products.object.Acknowledgement;
import com.myRetail.products.object.Product;
import com.myRetail.products.object.ProductResponse;
import com.myRetail.products.repository.RedSkyRepository;

@Service
public class FirebaseService {
	Firestore dbFirestore;
	DocumentSnapshot document;

	@Autowired
	RedSkyRepository repository;

	// GET Service method to retrieve the content from firebase db on the basis of
	// product_id provided
	public ProductResponse getProductDetails(String id) throws InterruptedException, ExecutionException {
		document = getDocumentSnapshot(id);
		if (document.exists()) {
			Product product = document.toObject(Product.class);
			ProductResponse productResponse = new ProductResponse();
			productResponse.setId(product.getId());
			productResponse.setTitle(repository.getTitleFromExternalAPI(product.getId()));
			productResponse.setCurrent_price(product.getCurrent_price());
			return productResponse;
		} else {
			throw new DataNotFoundException("Product ID: "+id+" does not exist");
		}
	}

	// Post service method to check for document in firestore db and create it, if
	// one doesn't already exists
	public Acknowledgement saveProductDetails(Product product) throws InterruptedException, ExecutionException {
		document = getDocumentSnapshot(product.getId());
		if (document.exists()) {
			return null;
		} else {
			ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("products").document(product.getId())
					.set(product, SetOptions.merge());
			return getProductPostedAcknowledgement(collectionsApiFuture.get().getUpdateTime().toString());
		}
	}

	// PUT Service method to update the contents on the basis of id, and created one
	// if it doesn't already exists
	public Acknowledgement updateProductDetails(String id, Product product)
			throws InterruptedException, ExecutionException {
		dbFirestore = connectToFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("products").document(id).set(product,
				SetOptions.merge());
		return getProductPostedAcknowledgement(collectionsApiFuture.get().getUpdateTime().toString());
	}

//Method to extract the document snapshot from the firebase db	
	public DocumentSnapshot getDocumentSnapshot(String id) throws InterruptedException, ExecutionException {
		dbFirestore = connectToFirestore();
		DocumentReference documentReference = dbFirestore.collection("products").document(id);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		document = future.get();
		return document;
	}

//Method to connect to firestore client and to initiate access to firebase collection	
	public Firestore connectToFirestore() {
		return FirestoreClient.getFirestore();
	}

	public Acknowledgement getProductPostedAcknowledgement(String ack) {
		Acknowledgement acknowledgement = new Acknowledgement();
		acknowledgement.setUpdatedTime(ack);
		acknowledgement.setUpdateStatus("Successfully stored in DB");
		return acknowledgement;
	}

}
