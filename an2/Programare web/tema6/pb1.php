<?php
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $plecare = isset($_GET['plecare']) ? $_GET['plecare'] : '';
    $destinatie = isset($_GET['destinatie']) ? $_GET['destinatie'] : '';
    $direct = isset($_GET['direct']) ? true : false;
    $legatura = isset($_GET['legatura']) ? true : false;
    $servername = "localhost";
    $username = "root";
    $password = "";
    $database = "php";

    $conn = new mysqli($servername, $username, $password, $database);

    if ($conn->connect_error) {
        die("Conexiunea la baza de date a eșuat: " . $conn->connect_error);
    }

    $sql = "SELECT * FROM pb1 WHERE localitate_plecare = '$plecare' AND localitate_sosire = '$destinatie'";
    if ($direct) {
        $sql .= " AND tip_tren = 'direct'";
    }
    else if ($legatura) {
        $sql .= " AND tip_tren = 'legatura'";
    }
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            echo "Număr tren: " . $row["nr_tren"] . "<br>";
            echo "Tip tren: " . $row["tip_tren"] . "<br>";
            echo "Localitate plecare: " . $row["localitate_plecare"] . "<br>";
            echo "Localitate sosire: " . $row["localitate_sosire"] . "<br>";
            echo "Ora plecare: " . $row["ora_plecare"] . "<br>";
            echo "Ora sosire: " . $row["ora_sosire"] . "<br>";
            echo "<br>";
        }
    } else {
        echo "Nu s-au găsit trenuri disponibile pentru căutarea efectuată.";
    }
    $conn->close();
}

