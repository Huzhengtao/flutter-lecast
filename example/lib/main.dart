import 'package:flutter/material.dart';
import 'package:lecast/event_handler.dart';
import 'package:lecast/lecast.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static List device = [];
  String doubanMp4='http://vt1.doubanio.com/202201232236/df63837f421cd23738ae00f272bcd9f9/view/movie/M/402790553.mp4';
  String qqMp4='http://om.tc.qq.com/gzc_1000102_0b53m4adqaaaw4agmzgfnzq4az6dhbvqapca.f10217.mp4?name=Renrenmi~2022-01-22&vkey=15B5651DDB3DAA3E7CD843563257297D2E8979561C98AFCD8654BAF97A19B7637C1B8EAF557DE8E737F04F8905C6C175295BF8225E0EA4F983038E0FBBDC511F0605ADC41F30D93C8B4CEBC3CB250AC24EF010CD61F2F350A0AA93F954CDD4DDA54E15118BBC2F1A1CDC04F24EBE3A34385D3052D8CD8493949512B7C447C3DD399CFBB6CA88EF9B';

  String tvNosign='http://yd-vl.cztv.com/channels/lantian/channel01/720p.m3u8';
  String tv='http://yd-vl.cztv.com/channels/lantian/channel01/720p.m3u8?a=1000&d=72628cf9087479cd58b88946c87249d1&k=7a6c916a1a15564da8aa5f232e28fe1a&t=1642948720';

  String m3u8LT='http://1-2-3-4-5-6-7-6-5-4-3-2-1-5.66yk.cn/miaoparty/miaopartyzy/22a712c29f8ed7e1ea4a6def6a2e7388.m3u8';
  String m3u81920='http://1920.kfvideos.com/dao-zi-yuan-dou-shi-wo-er-zi/936d39bb764e34b5b14215a984994704e31c0094.m3u8';
  String m3u8renrenmi='http://cloud.renrenmi.cc:2323/video/1637731771/39190.m3u8';

  @override
  void initState() {
    super.initState();
    Lecast((event, data) {
        print('$event\t$data');
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: Wrap(
              children: [
                TextButton(
                    onPressed: () =>Lecast.init('',''),
                    child: Text('init-test')),
                TextButton(
                    onPressed: () => Lecast.startSearch(),
                    child: Text('startSearch')),
                TextButton(
                    onPressed: () => Lecast.stopSearch(),
                    child: Text('stopSearch')),
                TextButton(
                    onPressed: () =>
                        Lecast.connect('-3278748314416337658', 'TV DMR', '192.168.0.104'),
                    child: Text('connect')),
                TextButton(
                    onPressed: () =>
                        Lecast.disconnect('-3278748314416337658', 'TV DMR', '192.168.0.104'),
                    child: Text('disconnect')),
                TextButton(
                    onPressed: () => Lecast.getConnectedDevices().then((value) => print(value)),
                    child: Text('getConnectedDevices')),
                TextButton(
                    onPressed: () =>
                        Lecast.play(qqMp4),
                    child: Text('play')),
                TextButton(
                    onPressed: () => Lecast.pausePlay(),
                    child: Text('pausePlay')),
                TextButton(
                    onPressed: () => Lecast.resumePlay(),
                    child: Text('resumePlay')),
                TextButton(
                    onPressed: () => Lecast.stopPlay(),
                    child: Text('stopPlay')),
                TextButton(
                    onPressed: () => Lecast.addVolume(),
                    child: Text('addVolume')),
                TextButton(
                    onPressed: () => Lecast.subVolume(),
                    child: Text('subVolume')),
                Column(
                  children: device.map((e) => Text(e['name'])).toList(),
                )
              ],
            )),
      ),
    );
  }
}
