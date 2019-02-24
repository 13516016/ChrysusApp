package com.example.chrysus.util;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthWrapper {
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static String getFirebaseAuthUid(){
        return firebaseAuth.getUid();
    }
    public static void logoutFirebase(){
        firebaseAuth.signOut();
    }
}
