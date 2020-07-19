node {
    stage ('Checkout') {
        git branch:'master', url: 'https://github.com/uhafner/codingstyle.git'
    }

    stage ('Line and Branch Coverage') {
        withMaven(maven: 'mvn-default', mavenLocalRepo: '/var/data/m2repository', mavenOpts: '-Xmx768m -Xms512m') {
            sh "mvn -V -U -e jacoco:prepare-agent test jacoco:report -Dmaven.test.failure.ignore"
        }
        publishCoverage adapters: [jacocoAdapter('**/*/jacoco.xml')], sourceFileResolver: sourceFiles('STORE_ALL_BUILD')
    }
}
