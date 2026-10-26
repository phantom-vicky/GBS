module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        concat: {   
            dist1: {
                src: [
                    'app/nps/directives/*.js',     
                ],
                dest: 'app/nps.js',
            }
        },
		
        less: {
            dist: {
                src: [
                    'assets/css/source.less'
                ],
                dest: 'assets/css/style.css',
            }
            
        }
    });
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.registerTask('default', ['concat:dist1','less']);
};