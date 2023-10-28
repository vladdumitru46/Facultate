<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "trenuri";

$conn = mysqli_connect($servername, $username, $password, $database);

if (!$conn) {
    echo "<option>" ."nu". "</option>";
    die("Conexiunea la baza de date a eșuat: " . mysqli_connect_error());
}

$plecare = $_GET["plecare"];
$sql = "SELECT sosire FROM statii WHERE plecare=?;";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $plecare);
$stmt->execute();

$result = $stmt->get_result();
while ($row = $result->fetch_assoc()) {
    $sosire = $row["sosire"];
    echo "<option>" . $sosire . "</option>";
}
$conn->close();

