import { secrets } from './secrets';

export const environment = {
    production: true,
    apiUrl: 'http://localhost:8080/kitchen',
    cloudinaryCloudName: secrets.cloudinaryCloudName,
    cloudinaryAPIKey: secrets.cloudinaryAPIKey,
    cloudinaryAPISecret: secrets.cloudinaryAPISecret
};
