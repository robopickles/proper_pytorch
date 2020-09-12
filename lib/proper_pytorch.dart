
import 'dart:async';

import 'package:flutter/services.dart';

class ProperPytorch {
  static const MethodChannel _channel =
      const MethodChannel('proper_pytorch');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
