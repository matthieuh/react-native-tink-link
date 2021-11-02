import React, { useEffect, useState } from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { multiply, displayTinkLink } from 'react-native-tink-link';

export default function App() {
  const [result, setResult] = useState<number | undefined>();

  useEffect(() => {
    multiply(3, 7).then(setResult);
    displayTinkLink('123', '456').then((response) => console.log(response));
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
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
