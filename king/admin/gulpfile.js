'use strict';

var gulp = require('gulp');
var notify = require('gulp-notify');
notify({ message: 'lint js--sds--- ok' }) 
gulp.task('default', 
	    function(){
	notify({ message: 'lint js--sds--- ok' }) 
	  gulp.run('move');
	  gulp.watch('./gulpfile.js',function(){
	    notify({ message: 'lint js----- ok' }) 
	    //gulp.run('watch'); 
	  }); 
	});

gulp.task('move',function(){
	   gulp.src(['bower_components/jquery/dist/jquery.**.js'])
	       .pipe(gulp.dest('src/main/webapp/static/scripts/jquery/'));
	   gulp.src(['bower_components/jquery-validation/dist/jquery.**.js'])
       .pipe(gulp.dest('src/main/webapp/static/scripts/jquery-validation/'));
	   gulp.src(['bower_components/zTree_v3/js/jquery.ztree.**.js'])
       .pipe(gulp.dest('src/main/webapp/static/scripts/ztree/'));
	   gulp.src(['bower_components/zTree_v3/css/zTreeStyle/**'])
       .pipe(gulp.dest('src/main/webapp/static/css/ztree/'));
	   gulp.src(['bower_components/jquery-treetable/css/jquery.treetable.**'])
       .pipe(gulp.dest('src/main/webapp/static/css/jquery-treetable/'));
	   gulp.src(['bower_components/jquery-treetable/jquery.treetable.js'])
       .pipe(gulp.dest('src/main/webapp/static/scripts/jquery-treetable/'));
	});




