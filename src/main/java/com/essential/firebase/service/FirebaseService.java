package com.essential.firebase.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.essential.domain.CategoryDomain;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseService {
	
	public String saveUserDetails(CategoryDomain category) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("category").document().set(category);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public CategoryDomain getUserDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("category").document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        CategoryDomain category = null;

        if(document.exists()) {
        	category = document.toObject(CategoryDomain.class);
            return category;
        }else {
            return null;
        }
    }

    public String updateUserDetails(CategoryDomain category) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("category").document(category.getId()).set(category);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUser(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("category").document(id).delete();
        return "Document with ID "+id+" has been deleted";
    }
    
    public List<CategoryDomain> getAll() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference documentReference = dbFirestore.collection("category");
        ApiFuture<QuerySnapshot> future = documentReference.get();

        QuerySnapshot document = future.get();

        List<CategoryDomain> category = null;

        	category = document.toObjects(CategoryDomain.class);
            return category;
    }

}
