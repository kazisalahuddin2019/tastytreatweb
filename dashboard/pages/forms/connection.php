<?php
$con = mysqli_connect("localhost","root","1qaz21`");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
$db_select = mysqli_select_db($con, "tastytreat");
if (!$db_select) {
    die("Database selection failed: " . mysqli_error());
}

?>