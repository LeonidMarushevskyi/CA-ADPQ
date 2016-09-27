<?php 
require_once   "spikephpcoverage-0.6.6/src/CoverageRecorder.php";
require_once   "spikephpcoverage-0.6.6/src/reporter/HtmlCoverageReporter.php";
$reporter = new HtmlCoverageReporter("Code Coverage Report", "", "report");
$includePaths = array(".");
$excludePaths = array("test", "foo.php");
$cov = new CoverageRecorder($includePaths, $excludePaths, $reporter);
$cov->startInstrumentation();

$x=10;
$y=0;
$cov->stopInstrumentation();
$cov->generateReport();
$reporter->printTextSummary();



//$z=$x/$y;
//echo $z;
/*
$servername = "localhost";
$username = "root";
$password = "k1v2y3k4p5g6";
$dbname = "chhs";
$conn = mysqli_connect($servername, $username, $password,$dbname);
if(! $conn ) 
	{
		die("Connection failed: " . $conn);
	} 
*/
	
?>