import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:proper_pytorch/proper_pytorch.dart';

void main() {
  const MethodChannel channel = MethodChannel('proper_pytorch');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ProperPytorch.platformVersion, '42');
  });
}
