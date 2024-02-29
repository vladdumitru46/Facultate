<?php

$servername = "localhost";
$servername = "localhost";
$username = "root";
$password = "";
$database = "ajax";

$conn = mysqli_connect($servername, $username, $password, $database);

if (!$conn) {
    die("Conexiunea la baza de date a eșuat: " . mysqli_connect_error());
}

$id = isset($_GET["id"]) ? $_GET["id"] : 1;
$pid = isset($_GET["pid"]) ? $_GET["pid"] : 1;

$sql = "SELECT * FROM `pb2` where id <= ? and id > ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("ii", $id, $pid); // Utilizăm "ii" pentru a indica că ambele parametri sunt de tip întreg
$stmt->execute();

$result = $stmt->get_result();
$i = 0;
while ($row = $result->fetch_assoc()) {
    $nume = $row["Nume"];
    echo "<tr>";
    echo "<td>" . $nume . "</td>";
    echo "<td>" . $row["Prenume"] . "</td>";
    echo "<td>" . $row["Telefon"] . "</td>";
    echo "<td>" . $row["Email"] . "</td>";
    echo "</tr>";
    $i += 1;
}

$conn->close();

