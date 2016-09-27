<?php 
session_start(); 
if(!$_SESSION['email'])  
{
    header("Location:logout.php");//redirect to login page to secure the welcome page without login access.  
}   
include 'inc/header.php';
include 'inc/Left-sidebar.php';
include 'inc/Top-Bar.php';

  
?> 
     <!-- page content -->
        <div class="right_col" role="main">

          <br />
          <div class="">


            <div class="row">
              <div class="col-md-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Profile Summary</h2>
                      <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                        <!-- <div class="input-group">
                          <input type="text" class="form-control" placeholder="Search for...">
                          <span class="input-group-btn">
                              <button class="btn btn-default" type="button">Go!</button>
                          </span>
                        </div> -->
                      </div>
                    <div class="clearfix"></div>
                  </div>



                  <div class="x_content">
                    <div class="col-md-9 col-sm-12 col-xs-12">


                      <div class="animated flipInY col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="tile-stats">
                          <div class="icon"><i class="fa fa-caret-square-o-right"></i>
                          </div>
                          <div class="count">459</div>

                          <h3>Children</h3>
                          <p></p>
                        </div>
                      </div>

                      <div class="animated flipInY col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="tile-stats">
                          <div class="icon"><i class="fa fa-comments-o"></i>
                          </div>
                          <div class="count">110</div>

                          <h3>Care Homes</h3>
                          <p></p>
                        </div>
                      </div>
                      <div class="animated flipInY col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="tile-stats">
                          <div class="icon"><i class="fa fa-sort-amount-desc"></i>
                          </div>
                          <div class="count">88</div>

                          <h3>Parent</h3>
                          <p></p>
                        </div>
                      </div>
                      <div class="animated flipInY col-lg-6 col-md-6 col-sm-12 col-xs-12">
                        <div class="tile-stats">
                          <div class="icon"><i class="fa fa-check-square-o"></i>
                          </div>
                          <div class="count">29</div>

                          <h3>Orphanage</h3>
                          <p></p>
                        </div>
                      </div>


                    </div>

                    <div class="col-md-3 col-sm-12 col-xs-12">
                      <div class="x_panel Twitter-Model">
                        <div class="x_title">
                          <h2>Twitter</h2>
                          <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            
                          </ul>
                          <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                          <article class="media event">
                            <a class="pull-left date">
                              <p class="month">April</p>
                              <p class="day">23</p>
                            </a>
                            <div class="media-body">
                              <a class="title" href="#">Item One Title</a>
                              <p>Lorem ipsum dolor sit amet</p>
                            </div>
                          </article>
                          <article class="media event">
                            <a class="pull-left date">
                              <p class="month">April</p>
                              <p class="day">23</p>
                            </a>
                            <div class="media-body">
                              <a class="title" href="#">Item Two Title</a>
                              <p>Lorem ipsum dolor sit amet</p>
                            </div>
                          </article>
                          <article class="media event">
                            <a class="pull-left date">
                              <p class="month">April</p>
                              <p class="day">23</p>
                            </a>
                            <div class="media-body">
                              <a class="title" href="#">Item Two Title</a>
                              <p>Lorem ipsum dolor sit amet</p>
                            </div>
                          </article>
                          <article class="media event">
                            <a class="pull-left date">
                              <p class="month">April</p>
                              <p class="day">23</p>
                            </a>
                            <div class="media-body">
                              <a class="title" href="#">Item Two Title</a>
                              <p>Lorem ipsum dolor sit amet</p>
                            </div>
                          </article>
                          <article class="media event">
                            <a class="pull-left date">
                              <p class="month">April</p>
                              <p class="day">23</p>
                            </a>
                            <div class="media-body">
                              <a class="title" href="#">Item Three Title</a>
                              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                          </article>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>




            <div class="row">
              <div class="col-md-4">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Top Fosters</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <ul class="list-unstyled top_profiles scroll-view">
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-blue profile_thumb">
                          <i class="fa fa-user blue"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="col-md-4">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Top Care Homes</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <ul class="list-unstyled top_profiles scroll-view">
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-blue profile_thumb">
                          <i class="fa fa-user blue"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="col-md-4">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Top Orphanage</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <ul class="list-unstyled top_profiles scroll-view">
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-blue profile_thumb">
                          <i class="fa fa-user blue"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-aero profile_thumb">
                          <i class="fa fa-user aero"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                      <li class="media event">
                        <a class="pull-left border-green profile_thumb">
                          <i class="fa fa-user green"></i>
                        </a>
                        <div class="media-body">
                          <a class="title" href="#">Mr. John Doe</a>
                          <p><strong>Lorem </strong> ipsum dolr sit amet </p>
                          <p> <small>12 Month ....</small>
                          </p>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="col-md-12 col-sm-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Star Rating</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <li><a href="#">Settings 1</a>
                          </li>
                          <li><a href="#">Settings 2</a>
                          </li>
                        </ul>
                      </li>
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <h4>Star Ratings For Orphanages<small> Hover and click on a star</small></h4>
                    <div>
                      <div class="starrr stars"><a href="#" class="fa fa-star-o"></a><a href="#" class="fa fa-star-o"></a><a href="#" class="fa fa-star-o"></a><a href="#" class="fa fa-star-o"></a><a href="#" class="fa fa-star-o"></a></div>
                      You gave a rating of <span class="stars-count">0</span> star(s)
                    </div>

                    <p>Also you can give a default rating by adding attribute data-rating</p>
                    <div class="starrr stars-existing" data-rating="4"><a href="#" class="fa fa-star"></a><a href="#" class="fa fa-star"></a><a href="#" class="fa fa-star"></a><a href="#" class="fa fa-star"></a><a href="#" class="fa fa-star-o"></a></div>
                    You gave a rating of <span class="stars-count-existing">4</span> star(s)
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>




        <!-- /page content -->

<!-- footer content -->
<?php 
include 'inc/footer.php';
?>
<!-- /footer content -->
