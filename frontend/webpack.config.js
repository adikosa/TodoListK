const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'development',
    resolve: {
        extensions: ['.js', '.jsx']
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader'
            },
            {
                // Preprocess your css files
                // you can add additional loaders here (e.g. sass/less etc.)
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /.(jpg|jpeg|png|svg)$/,
                use: ['file-loader'],
            },
        ]
    },
    plugins: [new HtmlWebpackPlugin({
        template: './src/index.html'
    })],
    devServer: {
        historyApiFallback: true,
        port: 3000
    },
    externals: {
        config: JSON.stringify({
            BASE_API_URL: 'http://localhost:8080/api'
        })
    }
}