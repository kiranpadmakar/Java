pipeline {
	agent any
	tools {
		ant 'Ant Installation'
		jdk 'Java'
	}
	environment {
		GH_ACCESS_KEY = credentials('GitHub_KP_Cred')
	}
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
			}
		}
		stage('build'){
			steps {
				git {
					branch: "myJavaLocal"
					credentialsId: "GitHub_KP_Cred"
					url: "https://github.com/kiranpadmakar/Java.git"
				}

				withAnt {
					target "main"
				}
			}
		}
	
	}

}