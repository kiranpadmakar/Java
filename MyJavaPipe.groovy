pipeline {
	agent any
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
				export bDir="${JOB_NAME}_${BUILD_NUMBER}"
				bat 'mkdir $bDir'
			      }
		}
		stage('build'){
			steps {
				git (
					branch: 'myJavaLocal',
					credentialsId: 'KiranPadmakar',
					url: 'https://github.com/kiranpadmakar/Java.git'
				)
				withAnt(installation: 'ANT', jdk: 'Java'){
				bat 'ant main'
				}
			}
		}
	
	}

}
