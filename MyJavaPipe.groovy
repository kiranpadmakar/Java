pipeline {
	agent any
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
				powershell(". '.\\dir_Build.ps1'")
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
