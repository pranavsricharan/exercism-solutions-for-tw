export class translator {
  static translate(text) {
    return text.split(" ")
        .map(word => this.translateWord(word))
        .join(" ");
  }

  static translateWord(word) {
    if (this.beginsWithVowelSound(word)) {
      return word + "ay";
    }
    
    let groups = this.splitIfBeginsWithConsonantFollowedByQu(word)
        || this.splitIfHasYWithVowelSound(word)
        || this.splitIfBeginsWithConsonantSound(word);
   
    if (groups) {
      return groups[2] + groups[1] + "ay";
    }
  }

  static beginsWithVowelSound(word) {
    return word.match(/(^h?[aeiou]+|^[xy][^aeiou])/) ? true: false
  }

  static splitIfBeginsWithConsonantFollowedByQu(word) {
    return word.match(/^([^aeiou]*qu)(.*)/);
  }

  static splitIfHasYWithVowelSound(word) {
    return word.match(/^([^aeiou]+)(y.*)/)
  }

  static splitIfBeginsWithConsonantSound(word) {
    return word.match(/^([^aeiou]+)(.*)/);
  }
}
