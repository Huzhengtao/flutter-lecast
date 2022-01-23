import 'dart:async';

import 'package:flutter/services.dart';

abstract class LecastEventHandler {
  final Function(LecastEvent, dynamic) _listener;

  LecastEventHandler(Function(LecastEvent, dynamic) listener) : _listener = listener;

  Future<dynamic> handleEvent(MethodCall call) async {
    LecastEvent event = LecastEvent.values.byName(call.method);
    _listener(event, call.arguments);
  }
}

enum LecastEvent {
  //搜索
  searchTimeout,
  searchStop,
  searchSuccess,
  searchAuthError,
  //连接
  onConnect,
  //播放状态
  onLoading,
  onStart,
  onPause,
  onCompletion,
  onStop,
  onError,
  onPositionUpdate
}
