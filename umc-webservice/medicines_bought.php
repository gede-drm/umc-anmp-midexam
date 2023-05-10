<?php
$medicinesTransactions = [
    ["id" => "INV/PHAR-UBY/20221121033", "date" => "2022-11-21 13:35", "medicines" => [["name" => "Panadol Paracetamol 500mg", "quantity" => 1, "price" => 10550, "sub_total" => 10550], ["name" => "Entrostop 12 Tablets", "quantity" => 2, "price" => 8640, "sub_total" => 17280], ["name" => "Minyak Kayu Putih Cap Lang 60mL", "quantity" => 1, "price" => 18000, "sub_total" => 18000]], "total_price" => 45830],
    ["id" => "INV/PHAR-UBY/20230325125", "date" => "2023-03-25 16:35", "medicines" => [["name" => "Hexpharm Ranitidine 150mg", "quantity" => 1, "price" => 3600, "sub_total" => 3600]], "total_price" => 3600],
    ["id" => "INV/PHAR-UBY/20221121033", "date" => "2022-11-21 13:35", "medicines" => [["name" => "Minyak Kayu Putih Cap Lang 60mL", "quantity" => 2, "price" => 18000, "sub_total" => 36000], ["name" => "Counterpain Cream 60gr", "quantity" => 1, "price" => 85900, "sub_total" => 85900]], "total_price" => 121900]
];
if (isset($_GET['detail_id'])) {
    $idSearch = $_GET['detail_id'];
    $medicinesTransaction = null;
    for ($i = 0; $i < count($medicinesTransactions); $i++) {
        if ($medicinesTransactions[$i]['id'] == $idSearch) {
            $medicinesTransaction = $medicinesTransactions[$i]['medicines'];
            break;
        }
    }
    echo json_encode($medicinesTransaction);
} else {
    $medicinesTransactionsNoMed = [];
    for ($i = 0; $i < count($medicinesTransactions); $i++) {
        unset($medicinesTransactions[$i]['medicines']);
        array_push($medicinesTransactionsNoMed, $medicinesTransactions[$i]);
    }
    echo json_encode($medicinesTransactionsNoMed);
}
