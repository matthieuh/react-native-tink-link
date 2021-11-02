import { NativeModules, Platform } from 'react-native';
import { getTinkAccessToken, getTinkAccounts } from '../utils/fetch';

const LINKING_ERROR =
  `The package 'react-native-tink-link' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const TinkLink = NativeModules.TinkLink
  ? NativeModules.TinkLink
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return TinkLink.multiply(a, b);
}

export function displayTinkLink(a: string, b: string): Promise<string> {
  return TinkLink.displayTinkLink(a, b);
}

export function fetchTinkAccessToken(
  TINK_CLIENT_ID: String,
  TINK_CLIENT_SECRET: String,
  authorizationCode: String
) {
  return getTinkAccessToken(
    TINK_CLIENT_ID,
    TINK_CLIENT_SECRET,
    authorizationCode
  );
}

export function fetchTinkAccounts(accessToken: String) {
  return getTinkAccounts(accessToken);
}
