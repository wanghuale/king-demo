
var gulp = require('gulp');
var url = require('url');
var browserSync = require('browser-sync');
var proxy = require('proxy-middleware');
var staticPath = 'vapstatics';

//css 预处理
gulp.task('styles', function () {
  return gulp.src('src/frontend/styles/**/*.css');
});

gulp.task('serve', ['styles'], function () {
  var proxyOptions = url.parse('http://127.0.0.1:9001');
  proxyOptions.route = '/' + staticPath;
  browserSync({
    notify: false,
    port: 9001,
    host: '127.0.0.1',
    server: {
      baseDir: ['.tmp', 'src/frontend'],
      directory: true,
      middleware: [proxy(proxyOptions)],
      routes: {
        '/bower_components': 'bower_components'
      }
    }
  });
  
});  