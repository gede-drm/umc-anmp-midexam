<?php
$doctors = [
    ["id" => "1", "name" => "dr. Juan Alex", "specialty" => "General Practitioner", "description" => "Obtained his degree from Universitas Surabaya on 2023", "schedule" => "Mon-Wed 10.00-16.00", "fee"=>100000, "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/juan-alex-1.jpg"],
    ["id" => "2", "name" => "dr. Vanessa Regina", "specialty" => "General Practitioner", "description" => "Obtained her degree from Universitas Surabaya on 2023", "schedule" => "Thu-Fri 10.00-16.00\nSat 09.00-13.00", "fee"=>100000, "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/vanessa-regina-1.jpg"],
    ["id" => "3", "name" => "dr. Adriana Tan, Sp.A.", "specialty" => "Pediatrics", "description" => "Pediatric Specialist,\nObtained her first degree from Universitas Udayana, Bali on 2010. Finished her specialty degree at Universitas Airlangga, Surabaya on 2016.", "schedule" => "Mon 09.00-12.00\nWed 13.00-15.00\nFri 13.00-15.00", "fee"=>250000,  "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/adriana-tan-1.jpg"],
    ["id" => "4", "name" => "dr. Benjamin Liem, Sp.PD.", "specialty" => "Internal Medicine", "description" => "Internal Medicine Specialist,\nObtained his first degree from Universitas Airlangga, Surabaya on 2003. Finished his specialty degree at Universitas Airlangga, Surabaya on 2010", "schedule" => "Mon-Thu 09.00-13.00, 14.30-16.00\nFri 13.00-15.00", "fee"=>250000, "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/benjamin-liem-1.jpg"],
    ["id" => "5", "name" => "dr. Darryl Halim, Sp.A(K)., RCPCH.", "specialty" => "Pediatrics", "description" => "Pediatric Specialist,\nMember of Royal College of Paediatrics and Child Health from 2014. Obtained his first degree from Universitas Airlangga, Surabaya on 2002. Finished his specialty degree at Universitas Airlangga, Surabaya on 2008.", "schedule" => "Tue 09.00-12.00\nThu 13.00-15.00\nSat 09.00-13.00", "fee"=>350000,  "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/darryl-halim-1.jpg"],
    ["id" => "6", "name" => "drg. Kristine Thea, BDS, Sp.KG.", "specialty" => "Dentistry", "description" => "Teeth Conservation Specialty Dentist.\nObtained her first degree from National University of Singapore on 2016 and continued her specialist education at Universitas Airlangga, Surabaya on 2018", "schedule" => "Mon 09.00-11.00\nWed 11.00-13.00", "fee"=>150000,  "photo_url" => "https://www.geded.ip4amin.site/anmp-midexam/doctors/kristine-thea-1.jpg"],
];

if (isset($_GET['id']) && is_numeric($_GET['id'])) {
    $idSearch = $_GET['id'];
    $doctor = null;
    for ($i = 0; $i < count($doctors); $i++) {
        if ($doctors[$i]['id'] == $idSearch) {
            $doctor = $doctors[$i];
            break;
        }
    }
    echo json_encode($doctor);
} elseif (isset($_GET['specialty'])) {
    $specialty = $_GET['specialty'];
    $doctorSpecialty = [];
    for ($i = 0; $i < count($doctors); $i++) {
        if ($doctors[$i]['specialty'] == $specialty) {
            array_push($doctorSpecialty, $doctors[$i]);
        }
    }
    echo json_encode($doctorSpecialty);
} else {
    echo json_encode($doctors);
}
