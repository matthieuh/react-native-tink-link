const baseTinkUrl = 'https://api.tink.com';

export const getTinkAccessToken = async (
  TINK_CLIENT_ID: String,
  TINK_CLIENT_SECRET: String,
  authorizationCode: String
) => {
  const tinkAccessTokenUrl = `${baseTinkUrl}/api/v1/oauth/token`;

  let formBody = `code=${authorizationCode}&client_id=${TINK_CLIENT_ID}&client_secret=${TINK_CLIENT_SECRET}&grant_type=authorization_code`;

  const response = await fetch(tinkAccessTokenUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: formBody,
  });
  const responseData = await response.json();
  return responseData;
};

export const getTinkAccounts = async (accessToken: String) => {
  const tinkAccountsUrl = `${baseTinkUrl}/api/v1/accounts/list`;

  const response = await fetch(tinkAccountsUrl, {
    method: 'GET',
    headers: {
      Authorization: 'Bearer ' + accessToken,
    },
  });

  const responseData = await response.json();
  return responseData;
};
