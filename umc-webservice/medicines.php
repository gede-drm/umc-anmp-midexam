<?php
$medicines = [
    ["id" => "1", "name" => "Hexpharm Ranitidine 150mg", "price"=>3600, "description" => "Contains Ranitidine HCl 150mg. GERD and peptic-ulcer treatment", "category" => "Gastrointestinal", "image_url"=>"https://storage.googleapis.com/rxstorage/Product/large/Apotek_Online_Farmaku_com_Ranitidine_150_mg_Tab_Hexpharm3.jpg"],
    ["id" => "2", "name" => "Panadol Paracetamol 500mg", "price"=>10550, "description" => "Contains Paracetamol 500mg. Relieve mild or moderate pain (headache, tootache) and fever", "category" => "Paracetamol", "image_url"=>"https://d2qjkwm11akmwu.cloudfront.net/products/222822_24-8-2022_11-38-12-1665760972.jpeg"],
    ["id" => "3", "name" => "Minyak Kayu Putih Cap Lang 60mL", "price"=>18000, "description" => "Contains 100% Cajuput oil. Relieve stomachache, headache, nausea, itches", "category" => "External Use Only", "image_url"=>"https://storage.googleapis.com/rxstorage/Product/large/Apotik_Online_Farmaku.Com_Minyak_Kayu_Putih_Lang_60_ml_.jpg"],
    ["id" => "4", "name" => "Counterpain Cream 60gr", "price"=>85900, "description" => "Contains Methyl salicylate 102 mg, Menthol 54.4 mg, Eugenol 13.6 mg. Cream for temporary relief of minor aches, muscles and joint pains, and sprains", "category" => "External Use Only", "image_url"=>"https://media.nedigital.sg/fairprice/fpol/media/images/product/XL/11048106_XL1_20201105.jpg"],
    ["id" => "5", "name" => "Entrostop 12 Tablets", "price"=>8640, "description" => "Helps diarrhea, contains Active Attapulgit Coloid 650mg and Pectin 50mg", "category" => "Gastrointestinal", "image_url"=>"https://mitra100.com/public/uploads/products/meta/Ts4aYNr3XhK8jVnvlFFWSfT566ICH3VIaakYp2Zb.jpeg"],
];

if (isset($_GET['id']) && is_numeric($_GET['id'])) {
    $idSearch = $_GET['id'];
    $med = null;
    for ($i = 0; $i < count($medicines); $i++) {
        if ($medicines[$i]['id'] == $idSearch) {
            $med = $medicines[$i];
            break;
        }
    }
    echo json_encode($med);
} else {
    echo json_encode($medicines);
}
