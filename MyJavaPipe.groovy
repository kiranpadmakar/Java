pipeline {
	agent any
	tools {
		ant 'Ant Installation'
		jdk 'Java'
	}
	environment {
		GH_ACCESS_KEY = credentials('516a1c16-d17c-4b5d-9373-b84fedd10445')
	}
	stages {
		stage('Welcome'){
			steps {
				echo "Hello World!!! Welcome to my Java Build"
			}
		}
		stage('build'){
			steps {
				git (
					branch: 'myJavaLocal',
					credentialsId: '516a1c16-d17c-4b5d-9373-b84fedd10445',
					url: 'https://github.com/kiranpadmakar/Java.git'
				)
				withAnt (
					targets: 'main'
				)
			}
		}
	
	}

}