package com.essential.auth;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.essential.domain.CategoryDomain;
import com.essential.domain.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserDetailFirebase {

	public String saveUserDetails(User user) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document().set(user);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public UserDetails getUserDetails(String id) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection("user").document(id);
		ApiFuture<DocumentSnapshot> future = documentReference.get();

		DocumentSnapshot document = future.get();

		UserDetails user = null;

		if(document.exists()) {
			user = document.toObject(User.class);
			return user;
		}else {
			return null;
		}
	}

//	public String updateUserDetails(User user) throws InterruptedException, ExecutionException {
//		Firestore dbFirestore = FirestoreClient.getFirestore();
//		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document(user.getPhoneNo()).set(user);
//		return collectionsApiFuture.get().getUpdateTime().toString();
//	}

	public String deleteUser(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection("user").document(id).delete();
		return "Document with ID "+id+" has been deleted";
	}

	public List<User> getAll() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference documentReference = dbFirestore.collection("user");
		ApiFuture<QuerySnapshot> future = documentReference.get();

		QuerySnapshot document = future.get();

		List<User> user = null;

		user = document.toObjects(User.class);
		return user;
	}

}
