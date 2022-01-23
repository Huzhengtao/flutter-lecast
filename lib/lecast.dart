import 'package:flutter/services.dart';
import 'package:lecast/event_handler.dart';

class Lecast extends LecastEventHandler {
  static const MethodChannel _channel = MethodChannel('lecast');

  final void Function(LecastEvent, dynamic) listener;

  Lecast(this.listener) : super(listener) {
    _channel.setMethodCallHandler(handleEvent);
  }

  static init(String appId, String apiKey) {
    _channel.invokeMethod("init", [appId, apiKey]);
  }

  static startSearch() {
    _channel.invokeMethod("startSearch");
  }

  static stopSearch() {
    _channel.invokeMethod("stopSearch");
  }

  static connect(String uid, String ip, String name) {
    _channel.invokeMethod("connect", [uid, ip, name]);
  }

  static disconnect(String uid, String ip, String name) {
    _channel.invokeMethod("disconnect", [uid, ip, name]);
  }

  static Future getConnectedDevices() {
    return _channel.invokeMethod<List>("getConnectedDevices");
  }

  static play(String url) {
    _channel.invokeMethod("play", url);
  }

  static pausePlay() {
    _channel.invokeMethod("pausePlay");
  }

  static resumePlay() {
    _channel.invokeMethod("resumePlay");
  }

  static stopPlay() {
    _channel.invokeMethod("stopPlay");
  }

  static seekTo(int seconds) {
    _channel.invokeMethod("seekTo", seconds);
  }

  static addVolume() {
    _channel.invokeMethod("addVolume");
  }

  static subVolume() {
    _channel.invokeMethod("subVolume");
  }
}
