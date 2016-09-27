        <?php 		
		include 'dbconnection.php'; ?>
		<div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.php" class="site_title"><i class="fa fa-paw"></i> <span>CHHS</span></a>
            </div>
            <div class="clearfix"></div>
            <!-- menu profile quick info -->
            <div class="profile">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                 <?php  
				 $collection = $conn->tbl_profile;	
				 $larresult = $collection->findOne(array('_id' => $_SESSION['iduser']));
						    ?>						  
                <h2><?php echo $larresult['Name'];?></h2>				
              </div>
            </div>
            <!-- /menu profile quick info -->
            <br />
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
			  <?php if($larresult['Typeofuser']==1){ ?>
                <h3>Parent</h3>
			  <?php } else { ?>
			  <h3>Case Worker</h3>
			  <?php } ?>
                <ul class="nav side-menu">
				<?php if($larresult['Typeofuser']==1){ ?>
                  <li><a href="index.php"><i class="fa fa-home"></i>Home</a></li>
                  <li><a href="search.php"><i class="fa fa-edit"></i>Search</a></li>
                   <li><a href="message.php"><i class="fa fa-envelope"></i>Messages</a></li>
				  <?php } else {?>
				  <li><a href="message.php"><i class="fa fa-envelope"></i>Messages</a></li>
				  <?php } ?>                
                </ul>
              </div>
            </div>
            <!-- /sidebar menu -->
          </div>
        </div>