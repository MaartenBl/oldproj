import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) => Scaffold(
    //drawer: NavigationDrawerWidget(),
    appBar: AppBar(
      title: const Text('Login'),
      centerTitle: true,
      backgroundColor: Colors.green,
    ),
    body: Center(
      child: ElevatedButton(
        child: const Text(''),
        onPressed: (){},
      ),
    ),
  );
}