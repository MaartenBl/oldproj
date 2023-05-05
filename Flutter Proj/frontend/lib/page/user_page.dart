import 'package:flutter/material.dart';

class UserPage extends StatelessWidget {
  final String name;

  const UserPage({
    Key? key,
    required this.name,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(
      backgroundColor: Colors.pink,
      title: Text(name),
      centerTitle: true,
    ),
  );
}