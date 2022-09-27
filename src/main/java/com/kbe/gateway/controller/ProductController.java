package com.kbe.gateway.controller;

import com.kbe.gateway.rabbitmq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ProductController {

    @Autowired
    ProductSender productSender;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getProducts(@RequestParam(required = false) String currencyParam) {
        Date date = new Date();
        String dateString = (1900+date.getYear())+"-"+ (date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+ date.getSeconds()+"  ";
        System.out.println(dateString+"getProducts triggered");
        //String json = "{\"id\":0,\"name\":\"wunder\",\"hardware\":[{\"id\":0,\"type\":\"gpu\",\"name\":\"rx 6800\",\"description\":\"fast\",\"price\":222.42,\"stock\":5}";

                Currency currency;
        if(currencyParam == null) {
            System.out.println(dateString+"..without param.");
            currency = Currency.EUR;
        } else {
            System.out.println(dateString+"..with param: "+currencyParam);
            currency =  Currency.valueOf(currencyParam.toUpperCase());
        }
        /*
        Object response = productSender.sendGetProducts(currency);

        if(response != null) {
            return ResponseEntity.ok(response.toString());
        } else {
            return ResponseEntity.ok("error");
        }
        */

        String productString = "{ \"productlist\": [ {\n" +
                "    \"id\" : 84,\n" +
                "    \"name\" : \"PC 1\",\n" +
                "    \"hardware\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"type\" : \"CASE\",\n" +
                "      \"name\" : \"O11 Dynamic EVO Tempered Glass\",\n" +
                "      \"description\" : \"Size:Midi Color:black\",\n" +
                "      \"price\" : 179.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 11,\n" +
                "      \"type\" : \"RAM\",\n" +
                "      \"name\" : \"8GB Corsair Vengeance\",\n" +
                "      \"description\" : \"Capacity:8 MHz:2400\",\n" +
                "      \"price\" : 32.72999954223633,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 19,\n" +
                "      \"type\" : \"MOTHERBOARD\",\n" +
                "      \"name\" : \"MSI MPG B550 Gaming Plus AMD B550\",\n" +
                "      \"description\" : \"Socket:So.AM4 Formfactor:ATX\",\n" +
                "      \"price\" : 109.5999984741211,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 32,\n" +
                "      \"type\" : \"CPU\",\n" +
                "      \"name\" : \"Ryzen 7 5800X\",\n" +
                "      \"description\" : \"Cores:8 Thread:16 GHz:4.7 Socket:so.AM4\",\n" +
                "      \"price\" : 288.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 39,\n" +
                "      \"type\" : \"FAN\",\n" +
                "      \"name\" : \"UNI FAN SL140 RGB PWM\",\n" +
                "      \"description\" : \"Size:140 dB:30 RPM:1500\",\n" +
                "      \"price\" : 20.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 53,\n" +
                "      \"type\" : \"GPU\",\n" +
                "      \"name\" : \"RTX 3080\",\n" +
                "      \"description\" : \"MHz:1440 VRAM:10\",\n" +
                "      \"price\" : 909.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 67,\n" +
                "      \"type\" : \"PSU\",\n" +
                "      \"name\" : \"Straight Power 11 Modular\",\n" +
                "      \"description\" : \"Wattage:850 Rating:Plantinum\",\n" +
                "      \"price\" : 137.55999755859375,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 81,\n" +
                "      \"type\" : \"STORAGE\",\n" +
                "      \"name\" : \"1TB Crucial P2 M.2\",\n" +
                "      \"description\" : \"Speed:1800 Capacity:1000\",\n" +
                "      \"price\" : 84.95999908447266,\n" +
                "      \"stock\" : 10\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"id\" : 85,\n" +
                "    \"name\" : \"Monster PC\",\n" +
                "    \"hardware\" : [ {\n" +
                "      \"id\" : 9,\n" +
                "      \"type\" : \"CASE\",\n" +
                "      \"name\" : \"Core 1000 Mini Tower\",\n" +
                "      \"description\" : \"Size:Midi Color:black\",\n" +
                "      \"price\" : 44.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 18,\n" +
                "      \"type\" : \"RAM\",\n" +
                "      \"name\" : \"2x 32GB Kingston FURY Beast\",\n" +
                "      \"description\" : \"Capacity:64 MHz:3200\",\n" +
                "      \"price\" : 219.8699951171875,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 27,\n" +
                "      \"type\" : \"MOTHERBOARD\",\n" +
                "      \"name\" : \"Gigabyte X570 Aorus Xtreme\",\n" +
                "      \"description\" : \"Socket:So.AM4 Formfactor:EATX\",\n" +
                "      \"price\" : 604.8599853515625,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 38,\n" +
                "      \"type\" : \"CPU\",\n" +
                "      \"name\" : \"Core i9-12900k\",\n" +
                "      \"description\" : \"Cores:16 Thread:24 GHz:5.2 Socket:so.1700\",\n" +
                "      \"price\" : 596.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 45,\n" +
                "      \"type\" : \"FAN\",\n" +
                "      \"name\" : \"3x NB-eLoop FAN B14-PS\",\n" +
                "      \"description\" : \"Size:140 dB:24 RPM:1500\",\n" +
                "      \"price\" : 73.33999633789062,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 53,\n" +
                "      \"type\" : \"GPU\",\n" +
                "      \"name\" : \"RTX 3080\",\n" +
                "      \"description\" : \"MHz:1440 VRAM:10\",\n" +
                "      \"price\" : 909.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 68,\n" +
                "      \"type\" : \"PSU\",\n" +
                "      \"name\" : \"Dark Power 12 Modular\",\n" +
                "      \"description\" : \"Wattage:1000 Rating:Titanium\",\n" +
                "      \"price\" : 231.6699981689453,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 73,\n" +
                "      \"type\" : \"SOUNDCARD\",\n" +
                "      \"name\" : \"STRIX SOAR\",\n" +
                "      \"description\" : \"Output:7.1\",\n" +
                "      \"price\" : 90.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 83,\n" +
                "      \"type\" : \"STORAGE\",\n" +
                "      \"name\" : \"2TB PNY XLR8 CS3030 M.2 2280\",\n" +
                "      \"description\" : \"Speed:3000 Capacity:2000\",\n" +
                "      \"price\" : 205.0,\n" +
                "      \"stock\" : 10\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"id\" : 86,\n" +
                "    \"name\" : \"Spar Gamer PC\",\n" +
                "    \"hardware\" : [ {\n" +
                "      \"id\" : 5,\n" +
                "      \"type\" : \"CASE\",\n" +
                "      \"name\" : \"NZXT H210i - red\",\n" +
                "      \"description\" : \"Size:Mini Color:red\",\n" +
                "      \"price\" : 89.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 14,\n" +
                "      \"type\" : \"RAM\",\n" +
                "      \"name\" : \"16GB G.Skill Aegis\",\n" +
                "      \"description\" : \"Capacity:16 MHz:3200\",\n" +
                "      \"price\" : 56.5099983215332,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 23,\n" +
                "      \"type\" : \"MOTHERBOARD\",\n" +
                "      \"name\" : \"MSI B550M Bazooka\",\n" +
                "      \"description\" : \"Socket:So.AM4 Formfactor:mATX\",\n" +
                "      \"price\" : 97.7699966430664,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 36,\n" +
                "      \"type\" : \"CPU\",\n" +
                "      \"name\" : \"Core i7-12700\",\n" +
                "      \"description\" : \"Cores:12 Thread:20 GHz:4.8 Socket:so.1700\",\n" +
                "      \"price\" : 349.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 43,\n" +
                "      \"type\" : \"FAN\",\n" +
                "      \"name\" : \"Pure Wings 2\",\n" +
                "      \"description\" : \"Size:140 dB:18.8 RPM:1600\",\n" +
                "      \"price\" : 10.899999618530273,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 51,\n" +
                "      \"type\" : \"GPU\",\n" +
                "      \"name\" : \"RTX 3060\",\n" +
                "      \"description\" : \"MHz:1320 VRAM:12\",\n" +
                "      \"price\" : 424.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 63,\n" +
                "      \"type\" : \"PSU\",\n" +
                "      \"name\" : \"Pure Power 11 FM Modular\",\n" +
                "      \"description\" : \"Wattage:650 Rating:Gold\",\n" +
                "      \"price\" : 86.47000122070312,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 71,\n" +
                "      \"type\" : \"SOUNDCARD\",\n" +
                "      \"name\" : \"Xonar AE\",\n" +
                "      \"description\" : \"Output:7.1\",\n" +
                "      \"price\" : 70.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 78,\n" +
                "      \"type\" : \"STORAGE\",\n" +
                "      \"name\" : \"500GB Samsung SSD 870 EVO 2.5\",\n" +
                "      \"description\" : \"Speed:530 Capacity:500\",\n" +
                "      \"price\" : 64.0,\n" +
                "      \"stock\" : 10\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"id\" : 87,\n" +
                "    \"name\" : \"Office PC\",\n" +
                "    \"hardware\" : [ {\n" +
                "      \"id\" : 3,\n" +
                "      \"type\" : \"CASE\",\n" +
                "      \"name\" : \"NZXT H510i - black\",\n" +
                "      \"description\" : \"Size:Midi Color:black\",\n" +
                "      \"price\" : 82.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 11,\n" +
                "      \"type\" : \"RAM\",\n" +
                "      \"name\" : \"8GB Corsair Vengeance\",\n" +
                "      \"description\" : \"Capacity:8 MHz:2400\",\n" +
                "      \"price\" : 32.72999954223633,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 22,\n" +
                "      \"type\" : \"MOTHERBOARD\",\n" +
                "      \"name\" : \"ASRock PG Riptide Intel Z690\",\n" +
                "      \"description\" : \"Socket:So.1700 Formfactor:ATX\",\n" +
                "      \"price\" : 209.8699951171875,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 30,\n" +
                "      \"type\" : \"CPU\",\n" +
                "      \"name\" : \"Ryzen 5 5600X\",\n" +
                "      \"description\" : \"Cores:6 Thread:12 GHz:4.6 Socket:so.AM4\",\n" +
                "      \"price\" : 199.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 41,\n" +
                "      \"type\" : \"FAN\",\n" +
                "      \"name\" : \"NF-A12x25 PWM\",\n" +
                "      \"description\" : \"Size:120 dB:22.6 RPM:2000\",\n" +
                "      \"price\" : 29.899999618530273,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 49,\n" +
                "      \"type\" : \"GPU\",\n" +
                "      \"name\" : \"GTX 1660\",\n" +
                "      \"description\" : \"MHz:1530 VRAM:6\",\n" +
                "      \"price\" : 297.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 63,\n" +
                "      \"type\" : \"PSU\",\n" +
                "      \"name\" : \"Pure Power 11 FM Modular\",\n" +
                "      \"description\" : \"Wattage:650 Rating:Gold\",\n" +
                "      \"price\" : 86.47000122070312,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 72,\n" +
                "      \"type\" : \"SOUNDCARD\",\n" +
                "      \"name\" : \"Xonar SE\",\n" +
                "      \"description\" : \"Output:7.1\",\n" +
                "      \"price\" : 50.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 78,\n" +
                "      \"type\" : \"STORAGE\",\n" +
                "      \"name\" : \"500GB Samsung SSD 870 EVO 2.5\",\n" +
                "      \"description\" : \"Speed:530 Capacity:500\",\n" +
                "      \"price\" : 64.0,\n" +
                "      \"stock\" : 10\n" +
                "    } ]\n" +
                "  }, {\n" +
                "    \"id\" : 88,\n" +
                "    \"name\" : \"Multimedia PC\",\n" +
                "    \"hardware\" : [ {\n" +
                "      \"id\" : 5,\n" +
                "      \"type\" : \"CASE\",\n" +
                "      \"name\" : \"NZXT H210i - red\",\n" +
                "      \"description\" : \"Size:Mini Color:red\",\n" +
                "      \"price\" : 89.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 17,\n" +
                "      \"type\" : \"RAM\",\n" +
                "      \"name\" : \"2x 16GB G.Skill RipJaws V schwarz\",\n" +
                "      \"description\" : \"Capacity:32 MHz:3200\",\n" +
                "      \"price\" : 115.37000274658203,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 24,\n" +
                "      \"type\" : \"MOTHERBOARD\",\n" +
                "      \"name\" : \"ASRock X570M Pro4\",\n" +
                "      \"description\" : \"Socket:So.AM4 Formfactor:mATX\",\n" +
                "      \"price\" : 178.91000366210938,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 33,\n" +
                "      \"type\" : \"CPU\",\n" +
                "      \"name\" : \"Ryzen 5 5950X\",\n" +
                "      \"description\" : \"Cores:16 Thread:32 GHz:4.9 Socket:so.AM4\",\n" +
                "      \"price\" : 488.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 45,\n" +
                "      \"type\" : \"FAN\",\n" +
                "      \"name\" : \"3x NB-eLoop FAN B14-PS\",\n" +
                "      \"description\" : \"Size:140 dB:24 RPM:1500\",\n" +
                "      \"price\" : 73.33999633789062,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 51,\n" +
                "      \"type\" : \"GPU\",\n" +
                "      \"name\" : \"RTX 3060\",\n" +
                "      \"description\" : \"MHz:1320 VRAM:12\",\n" +
                "      \"price\" : 424.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 65,\n" +
                "      \"type\" : \"PSU\",\n" +
                "      \"name\" : \"CX-F RGB White Series Modular\",\n" +
                "      \"description\" : \"Wattage:750 Rating:Bronze\",\n" +
                "      \"price\" : 81.37000274658203,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 70,\n" +
                "      \"type\" : \"SOUNDCARD\",\n" +
                "      \"name\" : \"NU Audio Pro\",\n" +
                "      \"description\" : \"Output:7.1\",\n" +
                "      \"price\" : 312.0,\n" +
                "      \"stock\" : 10\n" +
                "    }, {\n" +
                "      \"id\" : 80,\n" +
                "      \"type\" : \"STORAGE\",\n" +
                "      \"name\" : \"500GB Samsung SSD 980 M.2\",\n" +
                "      \"description\" : \"Speed:2600 Capacity:500\",\n" +
                "      \"price\" : 49.900001525878906,\n" +
                "      \"stock\" : 10\n" +
                "    } ]\n" +
                "  } ] }";
        return ResponseEntity.ok(productString);
    }
/*
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<String> getProducts() {
        return getProducts("EUR");
    }*/

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/create", method = RequestMethod.POST)
    public void createProduct(
            @RequestParam String name,
            @RequestParam int[] hardwareIDs
    ) {
        System.out.println(hardwareIDs);
        productSender.sendCreateProduct(name, hardwareIDs);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/update", method = RequestMethod.PUT)
    public void updateProduct(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int[] hardwareIDs
    ) {
        productSender.sendUpdateProduct(id,name, hardwareIDs);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/products/delete", method = RequestMethod.DELETE)
    public void deleteProduct(
            @RequestParam int id
    ) {
        productSender.sendDeleteProduct(id);
    }


}
