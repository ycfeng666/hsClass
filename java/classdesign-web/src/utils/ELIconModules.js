export default function transElIconName(iconName) {
  return "i" + iconName.replace(/[A-Z]/g, (match) => "-" + match.toLowerCase());
}
