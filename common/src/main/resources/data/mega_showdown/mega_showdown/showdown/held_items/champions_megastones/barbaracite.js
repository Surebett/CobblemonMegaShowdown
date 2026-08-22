({
  name: "Barbaracite",
	spritenum: 564,
  megaStone: { "Barbaracle": "Barbaracle-Mega" },
  itemUser: ["Barbaracle"],
  onTakeItem(item, source) {
    return !item.megaStone?.[source.baseSpecies.baseSpecies];
  },
  num: 2581,
	gen: 9,
	isNonstandard: "Future",
})