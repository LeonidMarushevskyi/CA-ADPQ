var gulp = require('gulp'),
    uglifycss = require('gulp-uglifycss'),
    browserSync = require('browser-sync'),
    reload = browserSync.reload,
    sass = require('gulp-sass'),
    autoprefixer = require('gulp-autoprefixer'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    htmlmin = require('gulp-htmlmin'),
    rimraf = require('rimraf'),
    filesort = require('gulp-angular-filesort'),
    ngAnnotate = require('gulp-ng-annotate');


gulp.task('default', ['serve']);


gulp.task('minify', function() {
    return gulp.src('./src/app/**/*.js')
        .pipe(ngAnnotate())
        .pipe(concat('app.js'))
        .pipe(uglify())
        .pipe(gulp.dest('./src/ang'));
});

gulp.task('style', function () {
    //changed to use 'gulp-sass'
    return gulp.src('./sass/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer({
            browsers: ['last 4 versions'],
            cascade: false
        }))
        .pipe(uglifycss())
        .pipe(gulp.dest('src/asset/css'));
});

gulp.task('clean', function (cb) {
    rimraf('./dist/**/*', cb);
});
gulp.task('html', function(){
   return gulp.src(['src/*.html', 'src/**/*.html'])
       .pipe(htmlmin({collapseWhitespace: true}))
       .pipe(gulp.dest('dist'));
});
gulp.task('asset', function(){
    return gulp.src('src/asset/**')
        .pipe(gulp.dest('dist/asset'));
});
gulp.task('env', function(){
    return gulp.src('src/env.js')
        .pipe(gulp.dest('dist'));
});
gulp.task('ang', function(){
    return gulp.src('src/ang/**')
        .pipe(gulp.dest('dist/ang'));
});

gulp.task('build', ['style','minify','env','ang','html','asset']);

gulp.task('servebuild', function(){
    browserSync({
        notify: false,
        logPrefix: 'CWS Production',
        server: ['dist']
    });
});


gulp.task('serve', ['style','minify'], function () {
    browserSync({
        notify: false,
        logPrefix: 'CWS Development',
        server: ['src']
    });
    gulp.watch(['src/**/*.html'], reload);
    gulp.watch(['src/app/*.*'], reload);
    gulp.watch(['src/app/**/*.*'], ['minify', reload]);
    gulp.watch(['sass/*.*'], ['style', reload]);
    gulp.watch(['sass/*/*.*'], ['style', reload]);
    gulp.watch(['sass/*/*/*.*'], ['style', reload]);

});