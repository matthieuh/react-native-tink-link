import React, { useEffect, useState } from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import {
  multiply,
  displayTinkLink,
  fetchTinkAccessToken,
} from 'react-native-tink-link';

export default function App() {
  const [result, setResult] = useState<number | undefined>();

  useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      <Button
        title={'Send request'}
        onPress={() => fetchTinkAccessToken('123', '456', '789')}
      />
      <Text>Result: {result}</Text>
      <Button
        title={'Display Tink Link'}
        onPress={() =>
          displayTinkLink('123', '456').then((response) =>
            console.log(response)
          )
        }
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
