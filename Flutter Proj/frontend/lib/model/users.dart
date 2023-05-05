import 'package:cloud_firestore/cloud_firestore.dart';

class Users {
  final String? displayname;
  final String? email;
  final String? role;
  final String? uid;

  Users({
    this.displayname,
    this.email,
    this.role,
    this.uid,
  });

  factory Users.fromFirestore(
      DocumentSnapshot<Map<String, dynamic>> snapshot,
      SnapshotOptions? options,
      ) {
    final data = snapshot.data();
    return Users(
      displayname: data?['displayname'],
      email: data?['email'],
      role: data?['role'],
      uid: data?['uid'],
    );
  }

  Map<String, dynamic> toFirestore() {
    return {
      if (displayname != null) "displayname": displayname,
      if (email != null) "email": email,
      if (role != null) "role": role,
      if (uid != null) "uid": uid,
    };
  }
}