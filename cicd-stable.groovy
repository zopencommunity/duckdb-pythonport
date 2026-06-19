node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/duckdb-pythonport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/duckdb-pythonport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'DuckDB in-process analytical database Python package'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
