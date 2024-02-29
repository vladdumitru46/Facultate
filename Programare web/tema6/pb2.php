<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $number = isset($_GET['nrElem']) ? $_GET['nrElem'] : '';
    $servername = "localhost";
    $username = "root";
    $password = "";
    $database = "php";

    $conn = new mysqli($servername, $username, $password, $database);

    if ($conn->connect_error) {
        die("Conexiunea la baza de date a eșuat: " . $conn->connect_error);
    }

    if (!isset($_SESSION['increment'])) {
        $_SESSION['increment'] = 0;
    }
    if (!isset($_GET['prevNumber'])) {
        $_GET['prevNumber'] = 0;
    }

    $prevNumber = $_GET['prevNumber'];

    if (isset($_GET['action'])) {
        if ($_GET['action'] === 'Prev') {
            $number -= $_SESSION['increment'];
            $prevNumber -= $_SESSION['increment'];
        } elseif ($_GET['action'] === 'Next') {
            $number += $_SESSION['increment'];
            $prevNumber += $_SESSION['increment'];
        }
    }

    $_SESSION['increment'] = $number - $prevNumber;

    $sql = "SELECT * FROM pb2 WHERE id <= '$number' AND id > '$prevNumber';";
    $result = $conn->query($sql);
    while ($row = $result->fetch_assoc()) {
        echo "Nume produs: " . $row["nume"] . ", specificatii: " . $row["specificatii"] .  "<br>";
    }
    echo "<form action='pb2.php' method='GET'>";
    echo "<input type='hidden' name='nrElem' value='$number'>";
    echo "<input type='hidden' name='prevNumber' value='$prevNumber'>";
    echo "<input type='submit' name='action' value='Prev'>";
    echo "<input type='submit' name='action' value='Next'>";
    echo "</form>";

    $conn->close();
}
?>
