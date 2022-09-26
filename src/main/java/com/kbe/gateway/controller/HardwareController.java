package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.Currency;
import com.kbe.gateway.rabbitmq.HardwareSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class HardwareController {


    String hardwarelistJson = "[{\n" +
            "    \"id\" : 1,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"O11 Dynamic EVO Tempered Glass\",\n" +
            "    \"description\" : \"Size:Midi Color:black\",\n" +
            "    \"price\" : 179.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 2,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"O12 Dynamic EVO Tempered Glass\",\n" +
            "    \"description\" : \"Size:Midi Color:white\",\n" +
            "    \"price\" : 189.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 3,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"NZXT H510i - black\",\n" +
            "    \"description\" : \"Size:Midi Color:black\",\n" +
            "    \"price\" : 82.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 4,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"NZXT H510i - white\",\n" +
            "    \"description\" : \"Size:Midi Color:white\",\n" +
            "    \"price\" : 85.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 5,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"NZXT H210i - red\",\n" +
            "    \"description\" : \"Size:Mini Color:red\",\n" +
            "    \"price\" : 89.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 6,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"NZXT H710 - black\",\n" +
            "    \"description\" : \"Size:Midi Conor:black\",\n" +
            "    \"price\" : 103.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 7,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"Dark Base Pro 900\",\n" +
            "    \"description\" : \"Size:Big Color:black\",\n" +
            "    \"price\" : 214.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 8,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"Define 7 Clear Tempered Glass\",\n" +
            "    \"description\" : \"Size:Big Color:white\",\n" +
            "    \"price\" : 184.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 9,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"Core 1000 Mini Tower\",\n" +
            "    \"description\" : \"Size:Midi Color:black\",\n" +
            "    \"price\" : 44.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 10,\n" +
            "    \"type\" : \"CASE\",\n" +
            "    \"name\" : \"Torrent Nano\",\n" +
            "    \"description\" : \"Size:Mini Color:white\",\n" +
            "    \"price\" : 125.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 11,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"8GB Corsair Vengeance\",\n" +
            "    \"description\" : \"Capacity:8 MHz:2400\",\n" +
            "    \"price\" : 32.72999954223633,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 12,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"8GB Corsair Vengeance RGB PRO schwarz\",\n" +
            "    \"description\" : \"Capacity:8 MHz:3200\",\n" +
            "    \"price\" : 45.91999816894531,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 13,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"2x8GB G.Skill RipJaws V silber\",\n" +
            "    \"description\" : \"Capacity:16 MHz:2400\",\n" +
            "    \"price\" : 62.40999984741211,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 14,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"16GB G.Skill Aegis\",\n" +
            "    \"description\" : \"Capacity:16 MHz:3200\",\n" +
            "    \"price\" : 56.5099983215332,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 15,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"2x 8GB G.Skill RipJaws V schwarz\",\n" +
            "    \"description\" : \"Capacity:16 MHz:3200\",\n" +
            "    \"price\" : 64.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 16,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"2x 8GB G.Skill RipJaws V schwarz\",\n" +
            "    \"description\" : \"Capacity:16 MHz:4000\",\n" +
            "    \"price\" : 88.37999725341797,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 17,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"2x 16GB G.Skill RipJaws V schwarz\",\n" +
            "    \"description\" : \"Capacity:32 MHz:3200\",\n" +
            "    \"price\" : 115.37000274658203,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 18,\n" +
            "    \"type\" : \"RAM\",\n" +
            "    \"name\" : \"2x 32GB Kingston FURY Beast\",\n" +
            "    \"description\" : \"Capacity:64 MHz:3200\",\n" +
            "    \"price\" : 219.8699951171875,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 19,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"MSI MPG B550 Gaming Plus AMD B550\",\n" +
            "    \"description\" : \"Socket:So.AM4 Formfactor:ATX\",\n" +
            "    \"price\" : 109.5999984741211,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 20,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"MSI MPG X570 GAMING PRO CARBON WIFI\",\n" +
            "    \"description\" : \"Socket:So.AM4 Formfactor:ATX\",\n" +
            "    \"price\" : 220.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 21,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"MSI Pro B660-A Intel B660\",\n" +
            "    \"description\" : \"Socket:So.1700 Formfactor:ATX\",\n" +
            "    \"price\" : 141.2100067138672,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 22,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"ASRock PG Riptide Intel Z690\",\n" +
            "    \"description\" : \"Socket:So.1700 Formfactor:ATX\",\n" +
            "    \"price\" : 209.8699951171875,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 23,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"MSI B550M Bazooka\",\n" +
            "    \"description\" : \"Socket:So.AM4 Formfactor:mATX\",\n" +
            "    \"price\" : 97.7699966430664,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 24,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"ASRock X570M Pro4\",\n" +
            "    \"description\" : \"Socket:So.AM4 Formfactor:mATX\",\n" +
            "    \"price\" : 178.91000366210938,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 25,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"MSI MAG Mortar Intel B660\",\n" +
            "    \"description\" : \"Socket:So.1700 Formfactor:mATX\",\n" +
            "    \"price\" : 175.27000427246094,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 26,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"ASRock Pro RS Intel B660\",\n" +
            "    \"description\" : \"Socket:So.1700 Formfactor:mATX\",\n" +
            "    \"price\" : 129.9199981689453,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 27,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"Gigabyte X570 Aorus Xtreme\",\n" +
            "    \"description\" : \"Socket:So.AM4 Formfactor:EATX\",\n" +
            "    \"price\" : 604.8599853515625,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 28,\n" +
            "    \"type\" : \"MOTHERBOARD\",\n" +
            "    \"name\" : \"ASRock B660M-ITX/ ac Intel B660\",\n" +
            "    \"description\" : \"Socket:So.1700 Formfactor:mini-ITX\",\n" +
            "    \"price\" : 146.9199981689453,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 29,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Ryzen 5 5500\",\n" +
            "    \"description\" : \"Cores:6 Thread:12 GHz:4.2 Socket:so.AM4\",\n" +
            "    \"price\" : 139.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 30,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Ryzen 5 5600X\",\n" +
            "    \"description\" : \"Cores:6 Thread:12 GHz:4.6 Socket:so.AM4\",\n" +
            "    \"price\" : 199.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 31,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Ryzen 7 5700X\",\n" +
            "    \"description\" : \"Cores:8 Thread:16 GHz:4.6 Socket:so.AM4\",\n" +
            "    \"price\" : 268.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 32,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Ryzen 7 5800X\",\n" +
            "    \"description\" : \"Cores:8 Thread:16 GHz:4.7 Socket:so.AM4\",\n" +
            "    \"price\" : 288.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 33,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Ryzen 5 5950X\",\n" +
            "    \"description\" : \"Cores:16 Thread:32 GHz:4.9 Socket:so.AM4\",\n" +
            "    \"price\" : 488.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 34,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Core i5-12400\",\n" +
            "    \"description\" : \"Cores:6 Thread:12 GHz:4.4 Socket:so.1700\",\n" +
            "    \"price\" : 189.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 35,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Core i5-12600\",\n" +
            "    \"description\" : \"Cores:6 Thread:12 GHz:4.8 Socket:so.1700\",\n" +
            "    \"price\" : 250.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 36,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Core i7-12700\",\n" +
            "    \"description\" : \"Cores:12 Thread:20 GHz:4.8 Socket:so.1700\",\n" +
            "    \"price\" : 349.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 37,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Core i7-12700K\",\n" +
            "    \"description\" : \"Cores:12 Thread:20 GHz:5 Socket:so.1700\",\n" +
            "    \"price\" : 409.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 38,\n" +
            "    \"type\" : \"CPU\",\n" +
            "    \"name\" : \"Core i9-12900k\",\n" +
            "    \"description\" : \"Cores:16 Thread:24 GHz:5.2 Socket:so.1700\",\n" +
            "    \"price\" : 596.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 39,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"UNI FAN SL140 RGB PWM\",\n" +
            "    \"description\" : \"Size:140 dB:30 RPM:1500\",\n" +
            "    \"price\" : 20.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 40,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"P12 PWM PST\",\n" +
            "    \"description\" : \"Size:120 dB:23.5 RPM:1800\",\n" +
            "    \"price\" : 6.380000114440918,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 41,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"NF-A12x25 PWM\",\n" +
            "    \"description\" : \"Size:120 dB:22.6 RPM:2000\",\n" +
            "    \"price\" : 29.899999618530273,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 42,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"Silent Wings 3\",\n" +
            "    \"description\" : \"Size:140 dB:28.1 RPM:1600\",\n" +
            "    \"price\" : 25.899999618530273,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 43,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"Pure Wings 2\",\n" +
            "    \"description\" : \"Size:140 dB:18.8 RPM:1600\",\n" +
            "    \"price\" : 10.899999618530273,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 44,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"2x UNI FAN SL140 RGB\",\n" +
            "    \"description\" : \"Size:140 dB:30 RPM:1500\",\n" +
            "    \"price\" : 68.2300033569336,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 45,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"3x NB-eLoop FAN B14-PS\",\n" +
            "    \"description\" : \"Size:140 dB:24 RPM:1500\",\n" +
            "    \"price\" : 73.33999633789062,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 46,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"NF-A20 PWM\",\n" +
            "    \"description\" : \"Size:200 dB:18.1 RPM:800\",\n" +
            "    \"price\" : 29.899999618530273,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 47,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"Spectre 230mm\",\n" +
            "    \"description\" : \"Size:230 dB:20 RPM:900\",\n" +
            "    \"price\" : 12.899999618530273,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 48,\n" +
            "    \"type\" : \"FAN\",\n" +
            "    \"name\" : \"3x UNI FAN SL120 RGB PWM\",\n" +
            "    \"description\" : \"Size:120 dB:31 RPM:1900\",\n" +
            "    \"price\" : 79.9000015258789,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 49,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"GTX 1660\",\n" +
            "    \"description\" : \"MHz:1530 VRAM:6\",\n" +
            "    \"price\" : 297.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 50,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RTX 3050\",\n" +
            "    \"description\" : \"MHz:1545 VRAM:8\",\n" +
            "    \"price\" : 324.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 51,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RTX 3060\",\n" +
            "    \"description\" : \"MHz:1320 VRAM:12\",\n" +
            "    \"price\" : 424.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 52,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RTX 3070\",\n" +
            "    \"description\" : \"MHz:1500 VRAM:8\",\n" +
            "    \"price\" : 638.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 53,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RTX 3080\",\n" +
            "    \"description\" : \"MHz:1440 VRAM:10\",\n" +
            "    \"price\" : 909.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 54,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RX 6500 XT\",\n" +
            "    \"description\" : \"MHz:2310 VRAM:4\",\n" +
            "    \"price\" : 179.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 55,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RX 6600 XT\",\n" +
            "    \"description\" : \"MHz:1968 VRAM:6\",\n" +
            "    \"price\" : 429.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 56,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RX 6700 XT\",\n" +
            "    \"description\" : \"MHz:1700 VRAM:12\",\n" +
            "    \"price\" : 549.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 57,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RX 6800 XT\",\n" +
            "    \"description\" : \"MHz:1825 VRAM:16\",\n" +
            "    \"price\" : 879.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 58,\n" +
            "    \"type\" : \"GPU\",\n" +
            "    \"name\" : \"RX 6900 XT\",\n" +
            "    \"description\" : \"MHz:1925 VRAM:16\",\n" +
            "    \"price\" : 987.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 59,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"System Power 9\",\n" +
            "    \"description\" : \"Wattage:400 Rating:Bronze\",\n" +
            "    \"price\" : 45.869998931884766,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 60,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Pure Power 11\",\n" +
            "    \"description\" : \"Wattage:500 Rating:Gold\",\n" +
            "    \"price\" : 60.540000915527344,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 61,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Pure Power 11 CM Modular\",\n" +
            "    \"description\" : \"Wattage:500 Rating:Gold\",\n" +
            "    \"price\" : 67.87000274658203,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 62,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Corsair CX Series CX650M Modular\",\n" +
            "    \"description\" : \"Wattage:650 Rating:Bronze\",\n" +
            "    \"price\" : 79.87000274658203,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 63,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Pure Power 11 FM Modular\",\n" +
            "    \"description\" : \"Wattage:650 Rating:Gold\",\n" +
            "    \"price\" : 86.47000122070312,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 64,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Corsair CV Series CV750\",\n" +
            "    \"description\" : \"Wattage:750 Rating:Bronze\",\n" +
            "    \"price\" : 52.72999954223633,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 65,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"CX-F RGB White Series Modular\",\n" +
            "    \"description\" : \"Wattage:750 Rating:Bronze\",\n" +
            "    \"price\" : 81.37000274658203,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 66,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Pure Power 11 FM Modular\",\n" +
            "    \"description\" : \"Wattage:750 Rating:Gold\",\n" +
            "    \"price\" : 97.83000183105469,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 67,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Straight Power 11 Modular\",\n" +
            "    \"description\" : \"Wattage:850 Rating:Plantinum\",\n" +
            "    \"price\" : 137.55999755859375,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 68,\n" +
            "    \"type\" : \"PSU\",\n" +
            "    \"name\" : \"Dark Power 12 Modular\",\n" +
            "    \"description\" : \"Wattage:1000 Rating:Titanium\",\n" +
            "    \"price\" : 231.6699981689453,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 69,\n" +
            "    \"type\" : \"SOUNDCARD\",\n" +
            "    \"name\" : \"Sound Blaster AE-7\",\n" +
            "    \"description\" : \"Output:7.1\",\n" +
            "    \"price\" : 223.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 70,\n" +
            "    \"type\" : \"SOUNDCARD\",\n" +
            "    \"name\" : \"NU Audio Pro\",\n" +
            "    \"description\" : \"Output:7.1\",\n" +
            "    \"price\" : 312.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 71,\n" +
            "    \"type\" : \"SOUNDCARD\",\n" +
            "    \"name\" : \"Xonar AE\",\n" +
            "    \"description\" : \"Output:7.1\",\n" +
            "    \"price\" : 70.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 72,\n" +
            "    \"type\" : \"SOUNDCARD\",\n" +
            "    \"name\" : \"Xonar SE\",\n" +
            "    \"description\" : \"Output:7.1\",\n" +
            "    \"price\" : 50.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 73,\n" +
            "    \"type\" : \"SOUNDCARD\",\n" +
            "    \"name\" : \"STRIX SOAR\",\n" +
            "    \"description\" : \"Output:7.1\",\n" +
            "    \"price\" : 90.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 74,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"120GB Kingston A400 2.5\",\n" +
            "    \"description\" : \"Speed:320 Capacity:120\",\n" +
            "    \"price\" : 22.469999313354492,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 75,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"120GB SanDisk Plus 2.5\",\n" +
            "    \"description\" : \"Speed:310 Capacity:120\",\n" +
            "    \"price\" : 23.579999923706055,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 76,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"240GB Crucial MX500 2.5\",\n" +
            "    \"description\" : \"Speed:510 Capacity:240\",\n" +
            "    \"price\" : 36.630001068115234,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 77,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"250GB Samsung SSD 870 EVO 2.5\",\n" +
            "    \"description\" : \"Speed:530 Capacity:250\",\n" +
            "    \"price\" : 43.97999954223633,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 78,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"500GB Samsung SSD 870 EVO 2.5\",\n" +
            "    \"description\" : \"Speed:530 Capacity:500\",\n" +
            "    \"price\" : 64.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 79,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"500GB SanDisk Ultra 3D 2.5\",\n" +
            "    \"description\" : \"Speed:530 Capacity:500\",\n" +
            "    \"price\" : 55.72999954223633,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 80,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"500GB Samsung SSD 980 M.2\",\n" +
            "    \"description\" : \"Speed:2600 Capacity:500\",\n" +
            "    \"price\" : 49.900001525878906,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 81,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"1TB Crucial P2 M.2\",\n" +
            "    \"description\" : \"Speed:1800 Capacity:1000\",\n" +
            "    \"price\" : 84.95999908447266,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 82,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"1TB Samsung SSD 980 M.2\",\n" +
            "    \"description\" : \"Speed:3000 Capacity:1000\",\n" +
            "    \"price\" : 92.0,\n" +
            "    \"stock\" : 10\n" +
            "  }, {\n" +
            "    \"id\" : 83,\n" +
            "    \"type\" : \"STORAGE\",\n" +
            "    \"name\" : \"2TB PNY XLR8 CS3030 M.2 2280\",\n" +
            "    \"description\" : \"Speed:3000 Capacity:2000\",\n" +
            "    \"price\" : 205.0,\n" +
            "    \"stock\" : 10\n" +
            "  }]";

    @Autowired
    HardwareSender hardwareSender;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/hardwarecomponents", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getHardwareComponents(@RequestParam(required = false) String currencyParam) {
        Date date = new Date();
        String dateString = (1900+date.getYear())+"-"+ (date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"  ";
        System.out.println(dateString+"getHardwareComponents triggered");

        //String json = "{\"id\":0,\"name\":\"wunder\",\"hardware\":[{\"id\":0,\"type\":\"gpu\",\"name\":\"rx 6800\",\"description\":\"fast\",\"price\":222.42,\"stock\":5}";

        Currency currency;
        if(currencyParam == null) {
            System.out.println(dateString+"..without param.");
            currency = Currency.EUR;
        } else {
            System.out.println(dateString+"..with param: "+currencyParam);
            currency =  Currency.valueOf(currencyParam.toUpperCase());
        }

        return ResponseEntity.ok(hardwarelistJson);

        /*Object response = hardwareSender.sendGetHardware(currency);

        if(response != null) {
            System.out.println(dateString+"..response: "+response);
            return ResponseEntity.ok(response.toString());
        } else {
            System.out.println(dateString+"..no response.");
            return ResponseEntity.ok(hardwarelistJson);
        }*/

    }
/*
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/hardwarecomponents", method = RequestMethod.GET)
    public ResponseEntity<String> getHardwareComponents() {
        return getHardwareComponents("EUR");
    }*/

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        //ResponseEntity<String> response = ResponseEntity.ok("Hello Anonymous");
        //System.out.println("response.toString(): "+response);
        Date date = new Date();
        String dateString = (1900+date.getYear())+"-"+ (date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"  ";
        System.out.println(dateString+"getAnonymous triggered");
        return ResponseEntity.ok("Hello Anonymous");
        //return response;
    }
}
