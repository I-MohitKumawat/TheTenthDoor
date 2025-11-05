from PIL import Image

# Define your color-to-number mapping
COLOR_MAP = {
    (49, 255, 0): 0,  # lime -> grass
    (0, 0, 0): 1,     # black -> wall
    (24, 124, 0): 2,  # green -> tree
    (251, 0, 255): 3, # grey ->rubble2 
    (136,0,138):4,    # rubble1
    (135, 89, 0): 5,  # brown -> mud
     (0,131,255):6,   #door
    (240, 11, 0): 7,  # chest
    (0, 80, 124): 8,  # water 
    (240,255,0):9,    #sand
}

def color_to_value(rgb):
    # Try exact match
    if rgb in COLOR_MAP:
        return COLOR_MAP[rgb]
    # If not exact, find closest color by Euclidean distance
    closest = min(COLOR_MAP.keys(), key=lambda c: sum((a-b)**2 for a, b in zip(c, rgb)))
    return COLOR_MAP[closest]

def image_to_array(image_path):
    img = Image.open(image_path).convert("RGB")
    width, height = img.size
    result = []

    for y in range(height):
        row = []
        for x in range(width):
            rgb = img.getpixel((x, y))
            row.append(color_to_value(rgb))
        result.append(row)

    return result


def print_map(array):
    for row in array:
        print(" ".join(map(str, row)))


# === Example usage ===
map_array = image_to_array("level1.png")  # replace with your file
print_map(map_array)