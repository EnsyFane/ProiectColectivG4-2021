const dotenv = require('dotenv');
const writeFile = require('fs').writeFile;

const targetPath = './src/environments/secrets.ts';

const envConfigFile = `export const secrets = {
    cloudinaryCloudName: '${process.env.CLOUDINARY_CLOUD_NAME}',
    cloudinaryAPIKey: '${process.env.CLOUDINARY_API_KEY}',
    cloudinaryAPISecret: '${process.env.CLOUDINARY_API_SECRET}'
};`;

writeFile(targetPath, envConfigFile, 'utf8', (err: any) => {
    if (err) {
        return console.log(err);
    }
});
