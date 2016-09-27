<?php 
session_start();  
if(!isset($_SESSION["email"]))
{
    header("Location: logout.php");//redirect to login page to secure the welcome page without login access.  
}
include 'inc/header.php';
include 'inc/Left-sidebar.php';
include 'inc/Top-Bar.php'; 
?>  
<script type="text/javascript">
  function fnopenreply(msgfrom,msg,idmsg,status)
  {
    
	 
       document.getElementById("replybox").style.display = "";
       document.getElementById("newmessagebox").style.display = "none";
       document.getElementById('viewmail').innerHTML = msg;
	   document.getElementById('replyto').value = msgfrom;
	   
       if(status ==0)
       {
	   
        $.ajax({
                type: "POST",
                url: 'updateviewstatus.php',
                data: {idmessage:idmsg},
                success: function(data){
                   //alert(data); 
                }
              });
       }
  }
  function fnopennewmessagebox()
  {
       document.getElementById("replybox").style.display = "none";
       document.getElementById("newmessagebox").style.display = "";
       
  }

  </script>
  
        <!-- page content -->
        <div class="right_col" role="main">  
            <div class="clearfix"></div>
            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Chat</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <div class="row">
                      <div class="col-sm-3 mail_list_column">
                        <button id="compose" class="btn btn-sm btn-success btn-block" onclick="fnopennewmessagebox()" type="button">COMPOSE</button>
                         <?php  
						    //$othermessage = "SELECT * FROM tbl_message where messageto='".$_SESSION['iduser']."' order by idmessage desc";
						   //order by idmessage desc";
						   //$result = mysqli_query($conn,$othermessage);
						  // echo $_SESSION['iduser'];
                   $collection = $conn->tbl_message;	
				  $result = $collection->find(array('messageto' => $_SESSION['iduser']));
//echo "<pre>";
//print_r($result);	
						   $meassagefromuser ="";
						   if (count($result) > 0) {	
                            //while($larresult = $result->fetch_assoc()) 
							$larresult =$result;
							foreach ($result as $larresult)
							{	
//echo "<pre>";
//print_r($larresult);	
//echo $larresult["messagefrom"];							
                                //$sqluserdetails = "SELECT * FROM tbl_profile where Idprofile='".$larresult["messagefrom"]."'";						   
						     //$userdetailsresult = mysqli_query($conn,$sqluserdetails);
						     //$laruser = $userdetailsresult->fetch_assoc();
							 //echo $larresult["messagefrom"];
							         $profilecollection = $conn->tbl_profile;
									// echo $larresult["messagefrom"];
									// findOne(array('_id' => new MongoId('47cc67093475061e3d9536d2')));
                                    $laruser = $profilecollection->findOne(array('_id' => new MongoId($larresult["messagefrom"])));	
									//echo "<pre>";
                                  // print_r($laruser);	
								   //die();	
                                    $meassagefromuser  =  $laruser["Email"];							
								 ?>								  
								   <a href="#" onclick="fnopenreply('<?php echo  rtrim($larresult["messagefrom"]);?>','<?php echo  rtrim($larresult["message"]);?>','<?php echo $larresult["_id"];?>','<?php echo $larresult["status"];?>')">
									  <div class="mail_list">
										<div class="left">
										  <i class="fa fa-circle-o"></i>
										</div>
										<div class="right">
										  <?php 
										if($larresult["status"]==1)
								        { ?>
										  <h3 style="color: green; background-color: #ffffff"><?php echo $larresult["message"];?><small style='text-align: right;'><?php echo date('j F, Y', strtotime($larresult["upddate"]) );?></small>&nbsp;&nbsp;<br/>	
										                                   <small><?php echo date('h:i A', strtotime($larresult["upddate"]) );?></small>
										                                  
										                                  </h3><b>
									    <?php } else {?>
										<h3><?php echo $larresult["message"];?><small style='text-align: right;'><?php echo date('j F, Y', strtotime($larresult["upddate"]) );?></small>&nbsp;&nbsp;<br/>	
										                                   <small><?php echo date('h:i A', strtotime($larresult["upddate"]) );?></small>
										                                  
										                                  </h3>
									    <?php } ?>
										</div>
									  </div>
									</a>								   
								<?php 
							}
							} else
							{
							   echo "0 Messages";
							}							  
							?> 
                      </div>
                      <!-- /MAIL LIST -->

                      <!-- CONTENT MAIL -->
                      <div class="col-sm-9 mail_view">
                        <div class="inbox-body" id='replybox' style="display: None">
                          <div class="mail_heading row">                            
                            <div class="col-md-12">
                              <h4> Message.</h4>
                            </div>
                          </div>
                          <div class="sender-info">
                            <div class="row">
                              <div class="col-md-12">                                
                                 <div>From</div>
								  <div><p><?php echo $meassagefromuser;?></p></div>
								  <strong>to</strong>
                                <strong>me</strong>                              
                              </div>
                            </div>
                          </div>
						  <br/>
                          <div class="view-mail" id='viewmail'>
                            <p> Text
							</p>
                          </div>
                          <div class="attachment">
                            <p>
                              <span><i class="fa fa-paperclip"></i> 3 attachments — </span>
                              <a href="#">Download all attachments</a> |
                              <a href="#">View all images</a>
                            </p>
                            <ul>
                              <li>
                                <a href="#" class="atch-thumb">
                                </a>
                                <div class="file-name">
                                  image-name.jpg
                                </div>
                                <span>12KB</span>
                                <div class="links">
                                  <a href="#">View</a> -
                                  <a href="#">Download</a>
                                </div>
                              </li>
                              <li>
                                <a href="#" class="atch-thumb">
                                </a>
                                <div class="file-name">
                                  image-name.jpg
                                </div>
                                <span>12KB</span>
                                <div class="links">
                                  <a href="#">View</a> -
                                  <a href="#">Download</a>
                                </div>
                              </li>
                              <li>
                                <a href="#" class="atch-thumb">
                                </a>
                                <div class="file-name">
                                  image-name.jpg
                                </div>
                                <span>12KB</span>
                                <div class="links">
                                  <a href="#">View</a> -
                                  <a href="#">Download</a>
                                </div>
                              </li>
                            </ul>
                          </div>
                      <!-- compose -->
					  <form action="storemessages.php" id='messageform' name='messageform' method='post' >
                        <div class="compose col-md-6 col-xs-12">
                            <div class="compose-header">
                              Reply
                            </div>
                            <div class="compose-body">
                              <div id="alerts"></div>
                              <div class="btn-toolbar editor" data-role="editor-toolbar" data-target="#editor"> 
							  <input type="hidden" id="replyto" name='replyto' class="form-control has-feedback-left" value=''>	
                              <textarea class="col-md-12 reply-msg" id="message" name='message'  placeholder="Message"></textarea>
                              <input type="hidden" id="messagefrom" name='messagefrom' class="form-control has-feedback-left" value='<?php echo $_SESSION['iduser'];?>'>
                              </div>
                            </div>
                          <!-- /compose -->
                          <div class="btn-group">
                            <button class="btn btn-sm btn-primary" type="submit"><i class="fa fa-reply"></i> Reply</button>
                            <button class="btn btn-sm btn-default" type="button"  data-placement="top" data-toggle="tooltip" data-original-title="Forward"><i class="fa fa-share"></i></button>
                            <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Print"><i class="fa fa-print"></i></button>
                            <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Trash"><i class="fa fa-trash-o"></i></button>
                          </div>
                        </div>
						</form>
                      </div>
					  <div id='newmessagebox' style="display: block">
					  <form action="/storemessages.php" id='messageform' name='messageform' method='post' >
                        <div class="compose col-md-6 col-xs-12">						    
                            <div class="compose-header">
                              New Message
							  
        <?php
		$profilecolln = $conn->tbl_profile;
									// echo $larresult["messagefrom"];
									// findOne(array('_id' => new MongoId('47cc67093475061e3d9536d2')));
        $larrresult = $profilecolln->findOne(array('_id' => new MongoId($_SESSION['iduser'])));	
		if($larrresult['Typeofuser'] ==1)
		{
		          //$pcollection = $conn->tbl_profile;	
				  $result = $profilecolln->find(array('Typeofuser' =>'2'));
		}
		else if($larrresult['Typeofuser'] ==2)
		{
		           //$pcollection = $conn->tbl_profile;	
				   $result = $profilecolln->find(array('Typeofuser' =>'1'));
		}	
		//echo $larrresult['Typeofuser'];
		//print_r($result);die();
		?>	
       
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback" style="margin-top: 5px;">
                             
							 <select id="messageto" name="messageto" class="form-control has-feedback-left" placeholder="To:">
							 <option selected="selected">Choose one</option>
							 <?php  foreach($result as $item){
					             //print_r($item);
								?>								
								<option value="<?php echo $item['Email']; ?>"><?php echo $item['Email']; ?></option>
								<?php
								}
								?>
					         </select>
                              <span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
                            </div>
                            <div class="compose-body">
                              <div id="alerts"></div>
                              <div class="btn-toolbar editor" data-role="editor-toolbar" data-target="#editor">                             
                              <textarea class="col-md-12 reply-msg" id="message" name='message' placeholder="New Message"></textarea>
							  <input type="hidden" id="messagefrom" name='messagefrom' class="form-control has-feedback-left" value='<?php echo $_SESSION['iduser'];?>'>
                              </div>
                            </div>
                          <!-- /compose -->
                          <div class="btn-group">						  
                            <button class="btn btn-sm btn-primary" type="submit"><i class="fa fa-reply"></i> Send</button>
                            <button class="btn btn-sm btn-default" type="button"  data-placement="top" data-toggle="tooltip" data-original-title="Forward"><i class="fa fa-share"></i></button>
                            <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Print"><i class="fa fa-print"></i></button>
                            <button class="btn btn-sm btn-default" type="button" data-placement="top" data-toggle="tooltip" data-original-title="Trash"><i class="fa fa-trash-o"></i></button>
                          </div>
                        </div>
                        </form>
					</div>
                      <!-- /CONTENT MAIL -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
        </div>
        <!-- /page content -->
<?php 
include 'inc/footer.php';
?>