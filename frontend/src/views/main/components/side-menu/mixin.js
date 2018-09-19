export default {
  methods: {
    showTitle (item) {
      return item.name;
    },
    showChildren (item) {
      return item.children && item.children.length !== 0
    }
  }
}
