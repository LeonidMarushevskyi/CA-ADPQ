var gulp        = require('gulp');
var sass        = require('gulp-sass');
var connect = require('gulp-connect-php');
var src = {
    scss: 'app/scss/*.scss',
    css:  'app/css'
};
gulp.task('connect', ['sass'], function() {
  connect.server({
    bin: 'C:/wamp/bin/php/php5.5.12/php.exe',
      port: 8888,
      base: 'app',
      livereload: true , 
      ini: 'C:/wamp/bin/php/php5.5.12/php.ini'
    });
    gulp.watch(src.scss, ['sass']);
});

gulp.task('php', function () {
  gulp.src('app/*.php')
    .pipe(connect.reload());
});
 
gulp.task('watch', function () {
  gulp.watch(['app/*.php'], ['php']);
});
gulp.task('sass', function() {
    return gulp.src(src.scss)
        .pipe(sass())
        .pipe(gulp.dest(src.css));
});

gulp.task('default', ['connect']);
