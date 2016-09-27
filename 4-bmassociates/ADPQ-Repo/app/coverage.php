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
	
?>