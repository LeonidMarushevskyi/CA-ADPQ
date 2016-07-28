var gulp = require('gulp');
var usemin = require('gulp-usemin');
var uglify = require('gulp-uglify');
var ngmin = require('gulp-ng-annotate');
var minifyCss = require('gulp-clean-css');
var sass = require('gulp-sass');
var jshint = require('gulp-jshint');
var lintspaces = require('gulp-lintspaces');
var stylish = require('jshint-stylish');
var karma = require('gulp-karma');
var flatten = require('gulp-flatten');
var rev = require('gulp-rev');
var cdnizer = require("gulp-cdnizer");
var revReplace = require("gulp-rev-replace");
var filter = require("gulp-filter");
var del = require('del');
var sourcemap = require('gulp-sourcemaps');

gulp.task('convert-sass', function () {
  return gulp.src('src/main/webapp/app/sass/*.scss')
          .pipe(sourcemap.init())
          .pipe(sass({
            outputStyle: 'compressed',
            includePaths: [
              'src/main/webapp/bower_components/bootstrap-sass-official/assets/stylesheets'
            ],
            sourcemaps: true
          }))
          .pipe(sourcemap.write('./'))
          .pipe(gulp.dest('src/main/webapp/app/css/'));
});


gulp.task('test', function () {
  return gulp.src('./blah')
          .pipe(karma({configFile: 'karma.conf.js', action: 'run'}))
          .on('error', function (err) {
            throw err;
          });
});

gulp.task('test-watch', function () {
  return gulp.src('./blah')
          .pipe(karma({
            configFile: 'karma.conf.js',
            action: 'watch'
          }));
});

gulp.task('usemin', ['scripts', 'formatting', 'convert-sass'], function () {
  return gulp.src('**/*.jsp', {cwd: 'src/main/webapp/'})
          .pipe(usemin({
            externalJs: [ngmin(), uglify()],
            appJs: [ngmin(), uglify()]
          }))
          .pipe(gulp.dest('target/chhs-frontend/'));
});


gulp.task('copy-fonts', [], function () {
  return gulp.src(['src/main/webapp/fonts/*.{ttf,woff,woff2,eot,svg}', 'src/main/webapp/bower_components/**/*.{ttf,woff,woff2,eot,svg}'])
          .pipe(flatten())
          .pipe(gulp.dest('target/frontend-app/fonts'));
});

gulp.task('scripts', function () {
  return gulp.src('app/**/*.js', {cwd: 'src/main/webapp/'})
          .pipe(jshint())
          .pipe(jshint.reporter(stylish))
});

gulp.task('formatting', function () {
  return gulp.src(['app/**/*.js', 'app/**/*.scss', 'app/**/*.jsp'], {cwd: 'src/main/webapp/'})
          .pipe(lintspaces({editorconfig: '.editorconfig'}))
          .pipe(lintspaces.reporter(stylish));
})

gulp.task('watch', function () {
  gulp.watch('src/main/webapp/app/sass/**/*.scss', ['convert-sass']);
  gulp.watch('src/main/webapp/app/**/*.js', ['scripts']);
});

gulp.task('ci', ['default']);

gulp.task('default', ['scripts', 'formatting', 'convert-sass']);

gulp.task('release', ['usemin', 'remove-bower'], function () {
  return cdned('');
});

gulp.task('remove-bower', function () {
  del([
    'target/frontend-app/bower_components'
  ]);
});

function cdned(cdn) {
  var cssFilter = filter('**/*.css', {restore: true});
  var jsFilter = filter('**/*.js', {restore: true});
  return gulp.src(['**/*.js', '**/*.css', '**/index.jsp', '!bower_components', ], {cwd: 'target/chhs-frontend/'})
          .pipe(jsFilter)
          .pipe(rev())
          .pipe(jsFilter.restore)
          .pipe(cssFilter)
          .pipe(rev())
          .pipe(cssFilter.restore)
          .pipe(revReplace({replaceInExtensions: ['.html', '.jsp']}))
          .pipe(cdnizer({
            defaultCDNBase: cdn,
            relativeRoot: '/',
            files: [
              '**/*.js',
              '**/styles.css'
            ]
          }))
          .pipe(gulp.dest('target/chhs-frontend/'));
}
