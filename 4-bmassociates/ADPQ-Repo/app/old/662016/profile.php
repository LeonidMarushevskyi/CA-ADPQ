<?php 
session_start(); 
if(!$_SESSION['email'])  
{
    header("Location: logout.php");//redirect to login page to secure the welcome page without login access.  
}  
include 'inc/header.php';
include 'inc/Left-sidebar.php';
include 'inc/Top-Bar.php';
?> 
        <!-- page content -->
        <div class="right_col" role="main">
          <div class="page-title">
            <div class="title_left">
              <h3>
                    Profile
                    <small>
                         <?php  //$result = mysqli_query($conn, $sql2);
						   // $sql = "SELECT * FROM tbl_profile where Idprofile='".$_SESSION['iduser']."'";
						   //$result = mysqli_query($conn,$sql);
						   //$larresult = $result->fetch_assoc(); 
						    $collection = $conn->tbl_profile;	
				            $larresult = $collection->findOne(array('_id' => $_SESSION['iduser']));		?>						  
                          <h2><?php echo $larresult['Name'];?></h2>
                    </small>
                </h3>
            </div>
            <div class="title_right">
              <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                <div class="input-group">
                  <input type="text" class="form-control" placeholder="Search for...">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Go!</button>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class="clearfix"></div>
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>User Report <small>Activity report</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                      <div class="profile_img">
                        <div id="crop-avatar">
                          <!-- Current avatar -->
                          <img class="img-responsive avatar-view" src="images/img.jpg" alt="Avatar" title="Change the avatar">
                        </div>
                      </div>
                      <h3><?php echo $larresult['Name'];?></h3>
                      <ul class="list-unstyled user_data">
                        <li>
                          <i class="fa fa-briefcase user-profile-icon"></i> <?php echo $larresult['Name'];?>
                        </li>						
                        <li><i class="fa fa-map-marker user-profile-icon"></i> <?php echo $larresult['Address'];?>
                        </li> 
                        <li class="m-top-xs">
                          <i class="fa fa-calendar-minus-o user-profile-icon"> </i> <?php echo $larresult['Phone'];?>
                        </li>
                       <li class="m-top-xs"> 
                          <i class="fa fa-send user-profile-icon"> </i> <?php echo $larresult['Email'];?>
                        </li>  
                      </ul>
                      <button type="button" class="btn btn-success" data-toggle="modal" data-target=".bs-example-modal-sm"><i class="fa fa-edit m-right-xs"></i>Edit Profile</button>
                      <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                          </button>
                          <h4 class="modal-title" id="myModalLabel">Edit</h4>
                        </div>
                        <div class="modal-body">						
                <form id="demo-form2" data-parsley-validate="" class="form-horizontal form-label-left" novalidate="" action="/updateprofile.php" method='post'>
                      <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="first-name">First Name<span class="required">*</span>
                        </label>
                          <input type="text" id="Name"  value='<?php echo $larresult['Name']; ?>' class="form-control col-xs-12" name="Name">
                        </div>
                      </div>
					  <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="first-name">Last Name<span class="required">*</span>
                        </label>
                          <input type="text" id="Lname"  value='<?php echo $larresult['Lname']; ?>' class="form-control col-xs-12" name="Lname">
                        </div>
                      </div>
                      <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="Location">Address<span class="required">*</span>
                        </label>
                          <input type="text" id="Location" value='<?php echo $larresult['Address']; ?>' name="Location" required="required" class="form-control col-md-12 col-xs-12">
                        </div>
                      </div>
                     <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="Location">Zip Code<span class="required">*</span>
                        </label>
                          <input type="text" id="Zipcode" value='<?php echo $larresult['Zipcode']; ?>' name="Zipcode" required="required" class="form-control col-md-12 col-xs-12">
                        </div>
                      </div> 
                      <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="Location">Date Of Birth<span class="required">*</span>
                        </label>
                          <input type="text" id="Db"  value='<?php echo $larresult['Dateofbirth']; ?>' name="Db" required="required" class="form-control col-md-12 col-xs-12">(YYYY-MM-DD)
                        </div>
                      </div>
                     <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label class="control-label" for="Location">Select Gender<span class="required">*</span>
                        </label>
                         <?php if($larresult['Gender'] == 1){ ?>						
						  <label class="radio-inline"><input type="radio" name="Gender" value='1' checked >Male</label>
						  <?php }else{ ?>
                          <label class="radio-inline"><input type="radio" name="Gender" value='1'>Male</label>
						  <?php } ?>
						  <?php if($larresult['Gender'] == 2){ ?>						
						  <label class="radio-inline"><input type="radio" name="Gender" value='2' checked >Female</label>
						  <?php }else{ ?>
                          <label class="radio-inline"><input type="radio" name="Gender" value='2'>Female</label>
						  <?php } ?>
                        </div>
                      </div>					  
                      <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label for="Phone" class="control-label">Phone</label>
                          <input id="Phone" class="form-control col-md-12 col-xs-12" type="text" value='<?php echo $larresult['Phone']; ?>' name="Phone">
                        </div>
                      </div>
					           <div class="form-group">
                        
                        <div class="col-md-12 col-sm-12 col-xs-12">
                          <label for="Email" class="control-label">Email</label>
                          <input id="Email" class="form-control col-md-12 col-xs-12" type="text" name="Email" value='<?php echo $larresult['Email']; ?>'>
                        </div>
                      </div> 
                      <div class="ln_solid"></div>
                      <div class="form-group">
                        <div class="col-md-12 col-sm-12 col-xs-12 align-right">
                          <button type="submit" class="btn btn-primary">Cancel</button>
                          <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                      </div>
                    </form>					
                        </div>
                      </div>
                    </div>
                  </div>
                      <br />
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                      <div class="profile_title">
                        <div class="col-md-6">
                          <h2>User Activity Report</h2>
                        </div>
                      </div>
                      <div class="" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                          <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Recent Activity</a>
                          </li>
                          <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">Adoptions</a>
                          </li>
                          <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">Profile</a>
                          </li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                          <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                            <!-- start recent activity -->
                            <ul class="messages">
                              <li>
                                <img src="images/img.jpg" class="avatar" alt="Avatar">
                                <div class="message_date">
                                  <h3 class="date text-info">24</h3>
                                  <p class="month">May</p>
                                </div>
                                <div class="message_wrapper">
                                  <h4 class="heading">Desmond Davison</h4>
                                  <blockquote class="message">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua butcher retro keffiyeh dreamcatcher synth.</blockquote>
                                  <br />
                                  <p class="url">
                                    <span class="fs1 text-info" aria-hidden="true" data-icon=""></span>
                                    <a href="#"><i class="fa fa-paperclip"></i> User Acceptance Test.doc </a>
                                  </p>
                                </div>
                              </li>
                              <li>
                                <img src="images/img.jpg" class="avatar" alt="Avatar">
                                <div class="message_date">
                                  <h3 class="date text-error">21</h3>
                                  <p class="month">May</p>
                                </div>
                                <div class="message_wrapper">
                                  <h4 class="heading">Brian Michaels</h4>
                                  <blockquote class="message">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua butcher retro keffiyeh dreamcatcher synth.</blockquote>
                                  <br />
                                  <p class="url">
                                    <span class="fs1" aria-hidden="true" data-icon=""></span>
                                    <a href="#" data-original-title="">Download</a>
                                  </p>
                                </div>
                              </li>
                              <li>
                                <img src="images/img.jpg" class="avatar" alt="Avatar">
                                <div class="message_date">
                                  <h3 class="date text-info">24</h3>
                                  <p class="month">May</p>
                                </div>
                                <div class="message_wrapper">
                                  <h4 class="heading">Desmond Davison</h4>
                                  <blockquote class="message">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua butcher retro keffiyeh dreamcatcher synth.</blockquote>
                                  <br />
                                  <p class="url">
                                    <span class="fs1 text-info" aria-hidden="true" data-icon=""></span>
                                    <a href="#"><i class="fa fa-paperclip"></i> User Acceptance Test.doc </a>
                                  </p>
                                </div>
                              </li>
                              <li>
                                <img src="images/img.jpg" class="avatar" alt="Avatar">
                                <div class="message_date">
                                  <h3 class="date text-error">21</h3>
                                  <p class="month">May</p>
                                </div>
                                <div class="message_wrapper">
                                  <h4 class="heading">Brian Michaels</h4>
                                  <blockquote class="message">Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua butcher retro keffiyeh dreamcatcher synth.</blockquote>
                                  <br />
                                  <p class="url">
                                    <span class="fs1" aria-hidden="true" data-icon=""></span>
                                    <a href="#" data-original-title="">Download</a>
                                  </p>
                                </div>
                              </li>
                            </ul>
                            <!-- end recent activity -->
                          </div>
                          <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                            <!-- start user projects -->
                            <table class="data table table-striped no-margin">
                              <thead>
                                <tr>
                                  <th>#</th>
                                  <th>Name</th>
                                  <th>Home</th>
                                  <th>Lorem</th>
                                  <th>Ipsum</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr>
                                  <td>1</td>
                                  <td>New Company Takeover Review</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">18</td>
                                  <td class="vertical-align-mid">
                                    fghfghfhfh
                                  </td>
                                </tr>
                                <tr>
                                  <td>2</td>
                                  <td>New Partner Contracts Consultanci</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">13</td>
                                  <td class="vertical-align-mid">
                                    fghfghfhfh
                                  </td>
                                </tr>
                                <tr>
                                  <td>3</td>
                                  <td>Partners and Inverstors report</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">30</td>
                                  <td class="vertical-align-mid">
                                    fghfghfhfh
                                  </td>
                                </tr>
                                <tr>
                                  <td>4</td>
                                  <td>New Company Takeover Review</td>
                                  <td>Deveint Inc</td>
                                  <td class="hidden-phone">28</td>
                                  <td class="vertical-align-mid">
                                    fghfghfhfh
                                  </td>
                                </tr>
                              </tbody>
                            </table>
                            <!-- end user projects -->
                          </div>
                          <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
                            <p>xxFood truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui
                              photo booth letterpress, commodo enim craft beer mlkshk </p>
                          </div>
                        </div>
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