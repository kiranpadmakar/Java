pipeline {
	agent any
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
				powershell 'mkdir dist'
				powershell 'cd dist'
				powershell '$dates=GET-DATE -FORMAT "yyyyMMddHHmmss"'
				powershell 'mkdir $dates'
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
