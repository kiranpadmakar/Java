pipeline {
	agent any
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
				ps 'mkdir dist'
				ps 'cd dist'
				ps '$dates=GET-DATE -FORMAT "yyyyMMddHHmmss"'
				ps 'mkdir $dates'
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
